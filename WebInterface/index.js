var server = require("./server");
var router = require("./router");
var home = require("./home");
var search = require("./search");

var handle = {};
handle["/"] = home.start;
handle["/home"] = home.start;
handle["/search"] = search.start;

server.start(router.route, handle);