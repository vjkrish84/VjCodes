var http = require("http");
var blazeface = require("@tensorflow-models/blazeface");
var session	= require('express-session');
var bodyParser = require('body-parser');
var express		=	require('express');
var lockSystem = require('lock-system');
var app	= express();
var fs = require('fs');




app.set('views', __dirname + '/views');
app.engine('html', require('ejs').renderFile);
app.set('view engine', 'ejs');

app.get('/capture',function(req,res){
	console.log('inside capture');
		res.render('index.html');
		
		
});

app.get('/lock',function(req,res){
	console.log('inside lock');
	lockSystem();		
});




var server = app.listen(8081,function(){
	console.log("App Started on PORT 8081");
	
});




// Console will print the message
console.log('Server running at http://127.0.0.1:8081/');