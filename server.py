from flask import Flask, jsonify, send_from_directory
import time

app = Flask(__name__)

bills = [
            {
                "id": 1,
                "billName": "Electricity Bill",
                "icon": "electricity.svg",
                "history": [
                    {"date": "2022-02-24", "amount": 313.08, "paid": True},
                    {"date": "2022-03-16", "amount": 334.09, "paid": True},
                    {"date": "2022-03-18", "amount": 354.74, "paid": True},
                    {"date": "2022-04-20", "amount": 80.26, "paid": True},
                    {"date": "2022-08-03", "amount": 496.67, "paid": True},
                    {"date": "2022-08-19", "amount": 142.05, "paid": True},
                    {"date": "2022-10-25", "amount": 283.29, "paid": True},
                    {"date": "2022-11-18", "amount": 352.79, "paid": False}
                ]
            },
            {
                "id": 2,
                "billName": "Internet Recharge",
                "icon": "wifi.svg",
                "history": [
                    {"date": "2022-03-02", "amount": 469.42, "paid": True},
                    {"date": "2022-03-06", "amount": 68.35, "paid": True},
                    {"date": "2022-04-21", "amount": 328.07, "paid": True},
                    {"date": "2022-06-04", "amount": 379.53, "paid": True},
                    {"date": "2022-08-19", "amount": 154.66, "paid": False},
                    {"date": "2022-11-16", "amount": 335.03, "paid": False},
                    {"date": "2022-12-14", "amount": 143.89, "paid": False}
                ]
            },
            {
                "id": 3,
                "billName": "Cable Bill",
                "icon": "cable.svg",
                "history": [
                    {"date": "2022-02-06", "amount": 342.62, "paid": False},
                    {"date": "2022-06-14", "amount": 310.25, "paid": True},
                    {"date": "2022-06-19", "amount": 353.45, "paid": False},
                    {"date": "2022-06-22", "amount": 310.28, "paid": False},
                    {"date": "2022-06-28", "amount": 469.05, "paid": True},
                    {"date": "2022-07-02", "amount": 363.45, "paid": True},
                    {"date": "2022-07-03", "amount": 170.78, "paid": True},
                    {"date": "2022-08-14", "amount": 140.96, "paid": True},
                    {"date": "2022-10-15", "amount": 91.91, "paid": True},
                    {"date": "2022-12-02", "amount": 293.19, "paid": False}
                ]
            },
            {
                "id": 4,
                "billName": "Mobile Recharge",
                "icon": "mobile.svg",
                "history": [
                    {"date": "2022-01-24", "amount": 424.31, "paid": True},
                    {"date": "2022-02-28", "amount": 485.05, "paid": True},
                    {"date": "2022-05-01", "amount": 248.67, "paid": False},
                    {"date": "2022-05-03", "amount": 223.72, "paid": False},
                    {"date": "2022-07-26", "amount": 326.61, "paid": False},
                    {"date": "2022-09-04", "amount": 157.59, "paid": False},
                    {"date": "2022-09-15", "amount": 474.1, "paid": True},
                    {"date": "2022-10-25", "amount": 347.15, "paid": False}
                ]
            }
]

credit_cards = [
    {
        "cardType": "VISA",
        "cardNumber": "**** **** **** 3854",
        "dueDate": "10th Oct",
        "amountDue": "$5,001.86",
        "paymentStatus": "EARLY"
    },
    {
        "cardType": "MASTERCARD",
        "cardNumber": "**** **** **** 1234",
        "dueDate": "15th Nov",
        "amountDue": "$3,500.00",
        "paymentStatus": "ON TIME"
    },
    {
        "cardType": "AMEX",
        "cardNumber": "**** **** **** 5678",
        "dueDate": "20th Dec",
        "amountDue": "$7,250.99",
        "paymentStatus": "LATE"
    }
]

car_subscription_card_content = {
    "priceText": "$399/M",
    "modelText": "Model X",
    "nextText": "NEXT",
    "billingDateText": "5th Oct",
    "progressText": "48/60",
    "progressFraction": 0.8,
    "imageContentDescription": "Car Image",
    "imageResourceId": "ic_car"
}

subscriptions = [car_subscription_card_content] * 3

@app.route('/bills')
def get_bills():
    return jsonify(bills)

@app.route('/credit-cards')
def get_credit_cards():
    time.sleep(2)
    return jsonify(credit_cards)

@app.route('/subscriptions')
def get_subscriptions():
    return jsonify(subscriptions)

@app.route('/images/<filename>')
def get_image(filename):
    return send_from_directory('images', filename)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=777, debug=True)

