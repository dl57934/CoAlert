from flask import Flask

app = Flask(__name__)

@app.route("/main")
def main():
    return 'start'

app.run()
