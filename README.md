## An Simple Example of Streaming Data Real Time Visualization

`realtime.js`: server-end code. It simulates streaming data: every 0.6 second, a new data point is sent to the front end via socket connection. I use node.js.  

`public/realtime.html`: front-end code. I use D3.js to draw and update a bar chart in real time. It receives streaming data from server end. 

==============
### How it works

1) Start server (node.js has been installed)
```
node realtime.js
```

2) Open `localhost:8080` in browser. Then you can see the bar chart is updated in real time. 
