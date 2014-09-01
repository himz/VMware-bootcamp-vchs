from pymongo import MongoClient


mongo_config = {}


def read_config():
    connection_config = {}
    with open('.config') as config_file:
        config_data = [line.strip() for line in config_file]
        connection_config['host'] = config_data[0]
        connection_config['port'] = int(config_data[1])
        return connection_config


def set_mongo_config():
    global mongo_config
    connection_config = read_config()
    mongo_config['client'] = MongoClient(connection_config['host'],
                                         connection_config['port'])
    mongo_config['db'] = mongo_config['client']['vwebstats']
    mongo_config['collection'] = mongo_config['db']['data']


set_mongo_config()
