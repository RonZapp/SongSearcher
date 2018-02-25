function getHomeCss() {
var css = '<head>' +
	'<link href=\'https://fonts.googleapis.com/css?family=Lobster\' rel=\'stylesheet\'>' +
	'<style>' +
	'body {' +
		'background-image: url("https://i.imgur.com/4dgOnBa.jpg");' +
		'background-size: cover;' +
		'background-color: indigo' +
		'background-position: center;' +
		'background-repeat: no-repeat;' +
		'background-attachment: fixed;' +
	'}' +
	'.outer-box {' +
		'height: 135px;' +
		'width: 600px;' +
		'padding: 5px;' +
		'position: absolute;' +
		'top: 50%;' +
		'left: 50%;' +
			/* -1*(height/2 + padding) 0 0 -1*(width/2 + passing) */
		'margin: -73px 0 0 -305px;' +

		'color: indigo;' +
		'background-color: #f4f7f8;' +
		'border: 5px solid indigo;' +
		'border-radius: 25px;' +
		'box-shadow: 0px 0px 15px 0px black;' +
	'}' +
	'.title {' +
		'text-align: center;' +
		'font-size: 40px;' +
		'margin-top: 15px;' +
		'margin-bottom: 20px;' +
		'font-family: \'Lobster\', cursive;' +
	'}' +
	'.search-bar {' +
		'text-align: center;' +
		'margin-bottom: 10px;' +
	'}' +
	'.search-text-field {' +
		'height: 28px;' +
		'border: 2px solid indigo;' +
		'border-radius: 3px;' +
	'}' +
	'.search-select {' +
		'height: 25px;' +
		'border: 2px solid indigo;' +
		'border-radius: 3px;' +
	'}' +
	'.search-button {' +
		'border: none;' +
    	'padding: 7px 14px 7px 14px;' +
    	'background: indigo;' +
	    'color: white;' +
	    '-webkit-appearance: none;' +
	    '-moz-appearance: none' +
	    'box-shadow: 1px 1px 4px #DADADA;' +
	    '-moz-box-shadow: 1px 1px 4px #DADADA;' +
	    '-webkit-box-shadow: 1px 1px 4px #DADADA;' +
	    'border-radius: 50px;' +
	    '-webkit-border-radius: 3px;' +
	    '-moz-border-radius: 3px;' +
	    'font-size: 12px;' +
	    'font-weight: bold;' +
	    '-webkit-transition: All 0.5s ease;' +
  		'-moz-transition: All 0.5s ease;' +
  		'-o-transition: All 0.5s ease;' +
  		'-ms-transition: All 0.5s ease;' +
  		'transition: All 0.5s ease;' +
	'}' +
	'.search-button:hover {background-color: #6e329b;}' +
	'box-shadow: 1px 1px 15px 1px black;' +
	'-moz-box-shadow: 1px 1px 15px 1px black;' +
	'-webkit-box-shadow: 1px 1px 15px 1px black;' +
	'</style>' +
	'</head>';

	return css;
}

exports.getHomeCss = getHomeCss;