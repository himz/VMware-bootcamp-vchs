from flask import Flask
from flask import render_template

from mongo_utils import get_records


app = Flask(__name__)


@app.route("/")
def index():
    return render_template('tl_index.html')


@app.route("/apis")
def apis():
    pass


@app.route("/requests")
def servers():
    pass


@app.route("/clients")
def clients():
    pass


if __name__ == "__main__":
    app.run(debug=True)
