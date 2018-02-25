var url = require('url'),	
	querystring = require('querystring')
	Connection = require('tedious').Connection,
	Request = require('tedious').Request;

/* runs when user requests /search page */
function start(request, response) {
	console.log("Recieved request for 'search'.")
	var searchHtml = '<html>' +
		'<h1>Song Recommender</h1>' + 
		'<form action="/search" method="get">' +
		  '<input type="text" name="searchText" placeholder="Song Search" size="50">' +
		  '<select name="searchType">' +
		    '<option value="artist">Search by artist</option>' +
		    '<option value="title">Search by title</option>' +
		    '<option value="tag">Search by tag</option>' +
		  '</select>' +
		  '<input type="submit" value="Search">' +
		'</form></html>';
	response.writeHead(200, {"Content_Type": "text/html"});
	response.write(searchHtml);

	var config = {
		userName: "SA",
		password: "<PasswordHiddenFromGitHub>", // Contact RonZapp for password
		server: "192.168.0.5",
		options: {
			port: 1401,
			database: 'SongRecommender'
		}
	}

	var query = url.parse(request.url).query;
	var searchText = querystring.parse(query)["searchText"];
	var searchType = querystring.parse(query)["searchType"];
	if (typeof searchText !== 'undefined' && searchText !== null && searchText !== "") {
		if (searchType === "artist" || searchType === "title" || searchType === "tag") {
			var connection = new Connection(config);
			connection.on('connect', function(err) {

				executeSqlQuery(connection, response, searchText, searchType);
			});
		} else {
			// No valid value for searchType
			response.write("<p2>Invalid parameters, please try again.</p2>");
			response.end();
		}
	} else {
		// No valid value for searchText
		response.write("<p2>Invalid parameters, please try again.</p2>");
		response.end();
	}
}

	/* Executes SQL query and sends results to writeHtml() */
function executeSqlQuery(connection, response, searchText, searchType) {
	var sql;
	if (searchType === "artist") {
		sql = "SELECT artist, title FROM songs WHERE track_id IN " +
  			"(SELECT similar_track_id FROM song_similar_maps WHERE " +
  			"artist IS NOT NULL AND track_id IN " +
  			"(SELECT track_id FROM songs WHERE artist = '" + searchText + "'))";
	} else if (searchType === "title") {
		sql = "SELECT artist, title  FROM songs WHERE track_id IN " +
  			"(SELECT similar_track_id FROM song_similar_maps WHERE " +
  			"artist IS NOT NULL AND track_id IN " +
  			"(SELECT track_id FROM songs WHERE title = '" + searchText + "'))";
	} else if (searchType === "tag") {
		sql = "SELECT artist, title FROM songs WHERE track_id IN " +
		"(SELECT track_id FROM song_tag_maps WHERE tag = '" + searchText + "')";
	} else {
		return new Error("search.executeSqlQuery: Invalid searchType");
	}

  	request = new Request(sql, function(err, rowCount) {
		if (err) {
			console.log(err);
			response.end();
		} else {
			console.log(rowCount + ' rows');
		}
		connection.close();
	});
	
	var sqlPromise = new Promise(function(resolve, reject) {
		var songs = {artists: [], titles: []};
		var switcher = 0;
		request.on('row', function(columns) {
			columns.forEach(function(column) {
				if (switcher === 0) {
					songs.artists.push(column.value);
					switcher = 1;
				} else {
					songs.titles.push(column.value);
					switcher = 0;
				}
			});
			resolve(songs);
		});
	});

	var keepPromise = function(response) {
		sqlPromise.then(function(songs) {
			writeHtml(response, songs);
		});
	};

	request.on('done', function(rowCount, more) {
		console.log(rowCount + ' rows returned');
	});

	connection.execSql(request);

	keepPromise(response);
}
	
	/* Writes results from sql query to page */
function writeHtml(response, songs) {
	var html = "<table>" + 
		"<tr><th>Artist</th><th>Title</th></tr>";

	for (i = 0; i < songs.artists.length; i++) {
		html += "<tr><td>" + songs.artists[i] + "</td>"
		+ "<td>" + songs.titles[i] + "</td></tr>";
	}
	html += "</table>"

	response.write(html);
	response.end();
}

exports.start = start;