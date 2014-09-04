import mongo_utils
from collections import defaultdict


def sum_api_paths(api_usage_data):
    # api_usage_data =  {'vApp/power/action/powerOn': 345,
    #      'vApp/power/action/powerOff': 585,
    #      'vApp/power/action/reset': 794,
    #      'admin/extension/action/registerserver': 696,
    #      'admin/extension/action/power/registerserver': 636,
    #     }
    mongo_api_data = defaultdict(int)
    for record in api_usage_data:
        mongo_api_data[record['api_path']] += 1
    return mongo_api_data


def process_split_paths(current_dict, paths, freq):
    if not paths:
        return None

    if len(paths) == 1:
        return {"name": paths[0], "size": freq}

    new_dict = {}
    new_dict["name"] = paths[0]
    new_dict["children"] = []
    inner_dict = process_split_paths(current_dict, paths[1:], freq)
    new_dict["children"].append(inner_dict)
    return new_dict


def get_api_usage_json():
    root_data = {}
    root_data["name"] = "root"
    root_data["children"] = []
    api_usage_data = mongo_utils.filter_records(['api_path'])
    api_usage_data = sum_api_paths(api_usage_data)
    for api_path, freq in api_usage_data.items():
        split_paths = api_path.split('/')
        current_dict = root_data

        if not current_dict["children"]:
            inner_dict = process_split_paths(current_dict, split_paths, freq)
            if inner_dict:
                current_dict["children"].append(inner_dict)

        else:
            children = current_dict["children"]
            i = 0
            while i < len(children):
                a_dict = children[i]
                i += 1
                if split_paths[0] == a_dict["name"]:
                    current_dict = a_dict
                    children = current_dict["children"]
                    split_paths = split_paths[1:]
                    i = 0

            inner_dict = process_split_paths(current_dict, split_paths, freq)
            current_dict["children"].append(inner_dict)
    return root_data


def get_browser_usage_json():
    browser_usage_data = {'data': [
            {'browser':'Chrome', 'total':'678'},
            {'browser':'Mozilla', 'total':'376'},
            {'browser':'Safari', 'total':'123'},
            {'browser':'IE', 'total':'23'}
        ]
    }

    # mongo_browser_data = mongo_utils.filter_records(['browser'])
    # browser_usage_data = defaultdict(int)
    # for record in mongo_browser_data:
    #     browser_usage_data[record['browser'].lower().strip()] += 1

    # browser_usage_data = {'data': [{'browser': str(k), 'total': v} for k, v in browser_usage_data.items()]}

    return browser_usage_data


def get_os_usage_json():
    # os_usage_data = {'data': [
    #         {'os':'Windows', 'total':'678'},
    #         {'os':'Linux', 'total':'376'},
    #         {'os':'Mac', 'total':'123'}
    #     ]
    # }
    mongo_os_data = mongo_utils.filter_records(['OS'])
    os_usage_data = defaultdict(int)
    for record in mongo_os_data:
        os_usage_data[record['OS'].lower().strip()] += 1

    os_usage_data = {'data': [{'os': str(k), 'total': v} for k, v in os_usage_data.items()]}
    return os_usage_data


def get_api_response_times():
    mongo_response_data = mongo_utils.filter_records(['api_path', 'response_time'])
    api_response_data = defaultdict(list)
    for record in mongo_response_data:
        api_response_data[record['api_path']].append(float(record['response_time']))

    for api_path, response_times in api_response_data.items():
        api_response_data[api_path] = sum(response_times)/len(response_times)

    api_response_data = {'data': [{'api_path': api_path, 'avg_response_time': avg_response_time} for api_path, avg_response_time in api_response_data.items()]}  
    return api_response_data
