# Java-Websocket-HttpServer
Application to get currency rates from websocket and publish on http server


Application use public websocket(_"wss://ws-feed.pro.coinbase.com"_), documentation -(https://docs.pro.coinbase.com/?r=1#websocket-feed) 

Data are published on http://localhost:8001/rates

Example output:

```
[
  {
    "last": "386.71",
    "ask": "386.71",
    "instrument": "ETHEUR",
    "time": "15:35:46",
    "bid": "386.61"
  },
  {
    "last": "16054.45",
    "ask": "16054.45",
    "instrument": "BTCUSD",
    "time": "15:35:47",
    "bid": "16053.40"
  },
  {
    "last": "456.59",
    "ask": "456.60",
    "instrument": "ETHUSD",
    "time": "15:35:46",
    "bid": "456.59"
  },
  {
    "last": "13600.99",
    "ask": "13600.99",
    "instrument": "BTCEUR",
    "time": "15:35:35",
    "bid": "13600.00"
  }
]
```

---

To run tests:
`gradle test`

To run application:
`gradle run`

Application run continuously. To stop use Ctrl + c.

---

Used websocket library: 
- jetty websocket client
