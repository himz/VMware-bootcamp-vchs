import mongo_utils


def sum_api_paths(api_usage_data):
    api_usage_data =  {'vApp/power/action/powerOn': 345,
         'vApp/power/action/powerOff': 585,
         'vApp/power/action/reset': 794,
         'admin/extension/action/registerserver': 696,
         'admin/extension/action/power/registerserver': 636,
        }
    return api_usage_data


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
    api_usage_data =  {'vApp/power/action/powerOn': 345,
         'vApp/power/action/powerOff': 585,
         'vApp/power/action/reset': 794,
         'admin/extension/action/registerserver': 696,
         'admin/extension/action/power/registerserver': 636,
        }

    root_data = {}
    root_data["name"] = "root"
    root_data["children"] = []
    # data = mongo_utils.filter_records('api_path')
    # data = sum_api_paths(data)
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
    return browser_usage_data


def get_os_usage_json():
    os_usage_data = {'data': [
            {'os':'Windows', 'total':'678'},
            {'os':'Linux', 'total':'376'},
            {'os':'Mac', 'total':'123'}
        ]
    }
    return os_usage_data
