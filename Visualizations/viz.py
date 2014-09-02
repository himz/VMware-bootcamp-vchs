from flask import Flask
from flask import render_template

import utils
import mongo_utils


app = Flask(__name__)


@app.route("/")
def index():
    return render_template('tl_index.html')


@app.route("/apis")
def apis():
    return render_template('tl_apis.html')


@app.route("/apis/most-used")
def get_most_used_apis():
    data = utils.get_api_usage_json()
    return render_template('tl_apis_most_used.html', data=data)


@app.route("/requests")
def servers():
    return render_template('tl_requests.html')


@app.route("/requests/response-time-verb")
def get_response_verb():
    data = {}
    data['records'] = filter_records(['http_verb', 'response_time'])
    return render_template('tl_requests_response_time_verb.html', data=data)


@app.route("/clients")
def clients():
    return render_template('tl_clients.html')


@app.route("/custom")
def custom():
    return render_template('tl_custom.html')


if __name__ == "__main__":
    app.run(debug=True, port=8090)
