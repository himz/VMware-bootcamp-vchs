from flask import Flask
from flask import render_template

import utils
import mongo_utils


app = Flask(__name__)


@app.route("/")
def index():
    return render_template('tl_index.html')


@app.route("/api-usage")
def get_most_used_apis():
    data = utils.get_api_usage_json()
    return render_template('tl_apis_most_used.html', data=data)


@app.route("/browser-usage")
def get_browser_distribution():
    data = utils.get_browser_usage_json()
    return render_template('tl_browser_usage.html', data=data)


@app.route("/requests/response-time-verb")
def get_response_verb():
    data = {}
    data['records'] = filter_records(['http_verb', 'response_time'])
    return render_template('tl_requests_response_time_verb.html', data=data)


@app.route("/os-usage")
def get_os_distribution():
    data = utils.get_os_usage_json()
    return render_template('tl_os_usage.html', data=data)


@app.route("/api-response")
def get_api_response_times():
    data = utils.get_api_response_times()
    return render_template('tl_api_response_times.html', data=data)


if __name__ == "__main__":
    app.run(debug=True, port=8090)
