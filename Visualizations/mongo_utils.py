import sys
import os.path


sys.path.append(os.path.join(os.path.dirname(__file__), '..'))
from Visualizations import mongo_config


collection = mongo_config['collection']

def filter_records(params):
    return collection.find(fields=params)
