var design = require("./design");

function start(request, response) {
	console.log("Recieved request for 'home'.")

	var html = '<html>' + design.getHomeCss() +
		'<body>' +
		  '<div class="outer-box">' +
		  '<form action="/search" method="get">' +
			'<h1 class="title">Song Searcher</h1>' +
			'<div class = "search-bar">' +
			  '<input class="search-text-field" type="text" name="searchText" placeholder=" Song Search" size="40">  ' +
			  '<select class="search-select" name="searchType">' +
				'<option value="artist">artist</option>' +
				'<option value="title">title</option>' +
				'<option value="tag">tag</option>' +
			  '</select> ' +
			  '<input class="search-button" type="submit" value="Search">' +
			'</div>' +
		  '</form>' +
		  '</div>' +
		'</body>';

	response.writeHead(200, {"Content-Type": "text/html"});
	response.write(html);
	response.end();
}

exports.start = start;