from flask import Flask
from flask import render_template

from mongo_utils import get_records


app = Flask(__name__)


@app.route("/")
def index():
    return render_template('tl_index.html')


@app.route("/apis")
def apis():
    return render_template('tl_apis.html')


@app.route("/requests")
def servers():
    return render_template('tl_requests.html')


@app.route("/clients")
def clients():
    return render_template('tl_clients.html')


@app.route("/custom")
def custom():
    return render_template('tl_custom.html')


if __name__ == "__main__":
    app.run(debug=True)
