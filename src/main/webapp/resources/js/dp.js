(function (cjs, an) {

var p; // shortcut to reference prototypes
var lib={};var ss={};var img={};
lib.webFontTxtInst = {}; 
var loadedTypekitCount = 0;
var loadedGoogleCount = 0;
var gFontsUpdateCacheList = [];
var tFontsUpdateCacheList = [];
lib.ssMetadata = [];



lib.updateListCache = function (cacheList) {		
	for(var i = 0; i < cacheList.length; i++) {		
		if(cacheList[i].cacheCanvas)		
			cacheList[i].updateCache();		
	}		
};		

lib.addElementsToCache = function (textInst, cacheList) {		
	var cur = textInst;		
	while(cur != null && cur != exportRoot) {		
		if(cacheList.indexOf(cur) != -1)		
			break;		
		cur = cur.parent;		
	}		
	if(cur != exportRoot) {		
		var cur2 = textInst;		
		var index = cacheList.indexOf(cur);		
		while(cur2 != null && cur2 != cur) {		
			cacheList.splice(index, 0, cur2);		
			cur2 = cur2.parent;		
			index++;		
		}		
	}		
	else {		
		cur = textInst;		
		while(cur != null && cur != exportRoot) {		
			cacheList.push(cur);		
			cur = cur.parent;		
		}		
	}		
};		

lib.gfontAvailable = function(family, totalGoogleCount) {		
	lib.properties.webfonts[family] = true;		
	var txtInst = lib.webFontTxtInst && lib.webFontTxtInst[family] || [];		
	for(var f = 0; f < txtInst.length; ++f)		
		lib.addElementsToCache(txtInst[f], gFontsUpdateCacheList);		

	loadedGoogleCount++;		
	if(loadedGoogleCount == totalGoogleCount) {		
		lib.updateListCache(gFontsUpdateCacheList);		
	}		
};		

lib.tfontAvailable = function(family, totalTypekitCount) {		
	lib.properties.webfonts[family] = true;		
	var txtInst = lib.webFontTxtInst && lib.webFontTxtInst[family] || [];		
	for(var f = 0; f < txtInst.length; ++f)		
		lib.addElementsToCache(txtInst[f], tFontsUpdateCacheList);		

	loadedTypekitCount++;		
	if(loadedTypekitCount == totalTypekitCount) {		
		lib.updateListCache(tFontsUpdateCacheList);		
	}		
};
// symbols:



(lib.Layer1 = function() {
	this.initialize(img.Layer1);
}).prototype = p = new cjs.Bitmap();
p.nominalBounds = new cjs.Rectangle(0,0,468,917);// helper functions:

function mc_symbol_clone() {
	var clone = this._cloneProps(new this.constructor(this.mode, this.startPosition, this.loop));
	clone.gotoAndStop(this.currentFrame);
	clone.paused = this.paused;
	clone.framerate = this.framerate;
	return clone;
}

function getMCSymbolPrototype(symbol, nominalBounds, frameBounds) {
	var prototype = cjs.extend(symbol, cjs.MovieClip);
	prototype.clone = mc_symbol_clone;
	prototype.nominalBounds = nominalBounds;
	prototype.frameBounds = frameBounds;
	return prototype;
	}


(lib.Tween26 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#00003A").s().p("AgUDvIAAg5IhoAAIAAhHIBoAAIAAhMIgNAAQgtAAgfggQghgfAAgtQAAgtAfgfQAfggAtgBIAPAAIAAg4IAxAAIAAA4IBgAAIAABHIhgAAIAABMIAFAAQAtAAAfAgQAgAfABAtQgBAtgfAgQgeAfgtABIgHAAIAAA5gAAdBvIAFAAQAQAAALgLQAKgLABgQQgBgQgKgLQgMgLgPAAIgFAAgAg8hkQgLALAAAQQAAAQALALQAMALAPAAIANAAIAAhMIgNAAQgPAAgMALg");
	this.shape.setTransform(94.6,0);

	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#00003A").s().p("AA4CjIgiiaIgpAAIghCaIg8AAIgtiaIg8AAIAAgsIAuAAIgmh/IA9AAIAiB/IA3AAIAZh0IBFAAIAZB0IA4AAIAih/IA8AAIglB/IAtAAIAAAsIg6AAIgsCagABVBMIAShDIghAAgAhTBMIAOhDIghAAgAAMgjIgMgyIgJAyIAVAAg");
	this.shape_1.setTransform(-95.4,0);

	this.shape_2 = new cjs.Shape();
	this.shape_2.graphics.f("#00003A").s().p("AAzC3Qg1AAgrgjQgpgigMg1IgwAAIAAgtIAtAAIAAgfIgtAAIAAgtIAwAAQAMg1ApgiQArgjA1AAIBgAAIAABHIhgAAQgZAAgVAOQgTAOgKAXIBbAAIAAAtIhhAAIAAAfIBhAAIAAAtIhbAAQAKAXATAOQAVAOAZAAIBgAAIAABHg");
	this.shape_2.setTransform(-34.2,0);

	this.shape_3 = new cjs.Shape();
	this.shape_3.graphics.f("#00003A").s().p("AghC3IAAhOIhKAAIAAgrIBKAAIAAgaIhKAAIAAgrIBJAAIh3ivIBRAAIABACIBHBnIBJhpIBRAAIh3CvIBJAAIAAArIhKAAIAAAaIBKAAIAAArIhKAAIAABOg");
	this.shape_3.setTransform(31.7,0);

	this.shape_4 = new cjs.Shape();
	this.shape_4.graphics.f("#00003A").s().p("AkZEaQh2h0AAimQAAikB2h2QB0h1ClAAQClAAB2B1QB1B2AACkQAACmh1B0Qh2B2ilAAQikAAh1h2gAj1j2QhnBnABCPQgBCQBnBmQBlBnCQAAQCQAABnhnQBlhmABiQQgBiPhlhnQhnhliQgBQiQABhlBlg");
	this.shape_4.setTransform(-94.9,0);

	this.shape_5 = new cjs.Shape();
	this.shape_5.graphics.f("#FFFFFF").s().p("AkIEJQhthuAAibQAAiaBthuQBuhtCaAAQCbAABuBtQBtBuAACaQAACbhtBuQhuBtibAAQiaAAhuhtg");
	this.shape_5.setTransform(-95,0);

	this.shape_6 = new cjs.Shape();
	this.shape_6.graphics.f("#00003A").s().p("AkaEaQh1h0AAimQAAikB1h2QB2h1CkAAQClAAB2B1QB1B2AACkQAACmh1B0Qh2B2ilAAQikAAh2h2gAj2j2QhlBnAACPQAACQBlBmQBmBnCQAAQCQAABmhnQBmhmAAiQQABiPhnhnQhmhliQgBQiQABhmBlg");
	this.shape_6.setTransform(-31.6,0);

	this.shape_7 = new cjs.Shape();
	this.shape_7.graphics.f("#FFFFFF").s().p("AkIEJQhthuAAibQAAiaBthuQBuhtCaAAQCbAABuBtQBtBuAACaQAACbhtBuQhuBtibAAQiaAAhuhtg");
	this.shape_7.setTransform(-31.7,0);

	this.shape_8 = new cjs.Shape();
	this.shape_8.graphics.f("#00003A").s().p("AkZEaQh2h0AAimQAAikB2h2QB0h1ClAAQCmAAB0B1QB2B2AACkQAACmh2B0Qh0B2imAAQilAAh0h2gAj1j2QhnBnAACPQAACQBnBmQBmBnCPAAQCQAABmhnQBnhmAAiQQAAiPhnhnQhmhliQgBQiPABhmBlg");
	this.shape_8.setTransform(31.7,0);

	this.shape_9 = new cjs.Shape();
	this.shape_9.graphics.f("#FFFFFF").s().p("AkIEJQhthuAAibQAAiaBthuQBuhtCaAAQCbAABuBtQBtBuAACaQAACbhtBuQhuBtibAAQiaAAhuhtg");
	this.shape_9.setTransform(31.6,0);

	this.shape_10 = new cjs.Shape();
	this.shape_10.graphics.f("#00003A").s().p("AkZEaQh2h0AAimQAAikB2h2QB0h1ClAAQClAAB2B1QB1B2AACkQAACmh1B0Qh2B2ilAAQilAAh0h2gAj1j2QhmBnAACPQAACQBmBmQBmBnCPAAQCQAABnhnQBlhmAAiQQAAiPhlhnQhnhliQgBQiPABhmBlg");
	this.shape_10.setTransform(95,0);

	this.shape_11 = new cjs.Shape();
	this.shape_11.graphics.f("#FFFFFF").s().p("AkIEJQhthuAAibQAAiaBthuQBuhtCaAAQCbAABuBtQBtBuAACaQAACbhtBuQhuBtibAAQiaAAhuhtg");
	this.shape_11.setTransform(94.9,0);

	this.timeline.addTween(cjs.Tween.get({}).to({state:[{t:this.shape_11},{t:this.shape_10},{t:this.shape_9},{t:this.shape_8},{t:this.shape_7},{t:this.shape_6},{t:this.shape_5},{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]}).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-134.9,-40,269.9,80);


(lib.Tween25 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.instance = new lib.Layer1();
	this.instance.parent = this;
	this.instance.setTransform(-135,-264.5,0.577,0.577);

	this.timeline.addTween(cjs.Tween.get(this.instance).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-135,-264.5,270,529);


(lib.Tween24 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.instance = new lib.Layer1();
	this.instance.parent = this;
	this.instance.setTransform(-135,-264.5,0.577,0.577);

	this.timeline.addTween(cjs.Tween.get(this.instance).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-135,-264.5,270,529);


(lib.Tween21 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#000000").s().p("AAdA4IAAhvIAPAAIAABvgAgsAVIAQgKIAKgIIAHgFIAEgGIABgIIABgIIAAgIIglAAIAAgNIAzAAIAAAWIAAALQgBAFgCAFQgCACgDAEIgHAJIgMAJIgSALg");
	this.shape.setTransform(16.3,-0.5);

	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#000000").s().p("AAVA4IAAhvIAPAAIAAAsIAQAAIAAALIgQAAIAAA4gAgeAdQgFgCgEgDQgDgDgCgEQgCgFAAgEIAAgHQAAgEACgEQACgEADgEQAEgCAFgCQAFgCAHgBQAGABAGACQAFACACACQAEAEACAEIABAIIAAAHQAAAEgBAFQgCAEgEADQgCADgFACQgGACgGAAQgHAAgFgCgAgYgIIgEACIgDAFIgBADIAAAFIABAEIADAEIAEADIAGABIAGgBIAEgDIADgEIAAgEIAAgFIAAgDIgDgFIgEgCIgGgBIgGABgAgzgaIAAgOIAZAAIAAgOIAQAAIAAAOIAXAAIAAAOg");
	this.shape_1.setTransform(5.6,-0.5);

	this.shape_2 = new cjs.Shape();
	this.shape_2.graphics.f("#000000").s().p("AAkA4IAAhvIAOAAIAABvgAANAzIAAg5IgNAAIAAgMIANAAIAAgjIAPAAIAABogAgxATIAIgIIAIgJIADgEIACgEIACgFIAAgGIAAgOIgSAAIAAgNIAxAAIAAANIgQAAIAAAOIAAAGIABAEIADAEIACADIAHAJIAJAJIgKAJIgGgIIgHgHIgDgEIgDgEIgDAEIgDAFIgIAIIgHAIg");
	this.shape_2.setTransform(-5.7,-0.5);

	this.shape_3 = new cjs.Shape();
	this.shape_3.graphics.f("#000000").s().p("AgZA5IAAghIA2AAIAAgIIg3AAIAAgMIBGAAIAAAgIg2AAIAAAIIA5AAIAAANgAgugHIAUgIQAIgDAEgDQAEgDABgDIABgHIAAgEIgiAAIAAgNIAwAAIAAASQAAAHgCAFQgCAFgFAEQgFAEgJAEIgYAIgAAdAAIAAgJIgTAAIAAgNIATAAIAAgJIgRAAIAAgNIARAAIAAgMIAPAAIAAA4g");
	this.shape_3.setTransform(-16.5,-0.4);

	this.shape_4 = new cjs.Shape();
	this.shape_4.graphics.f("#F5F5F5").s().p("AqECWQgVAAgPgPQgOgPAAgUIAAjHQAAgUAOgPQAPgPAVAAIUJAAQAUAAAPAPQAPAPAAAUIAADHQAAAUgPAPQgPAPgUAAg");

	this.timeline.addTween(cjs.Tween.get({}).to({state:[{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]}).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-69.5,-15,139,30);


(lib.Tween20 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#000000").s().p("AAdA4IAAhvIAPAAIAABvgAgsAVIAQgKIAKgIIAHgFIAEgGIABgIIABgIIAAgIIglAAIAAgNIAzAAIAAAWIAAALQgBAFgCAFQgCACgDAEIgHAJIgMAJIgSALg");
	this.shape.setTransform(16.3,-0.5);

	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#000000").s().p("AAVA4IAAhvIAPAAIAAAsIAQAAIAAALIgQAAIAAA4gAgeAdQgFgCgEgDQgDgDgCgEQgCgFAAgEIAAgHQAAgEACgEQACgEADgEQAEgCAFgCQAFgCAHgBQAGABAGACQAFACACACQAEAEACAEIABAIIAAAHQAAAEgBAFQgCAEgEADQgCADgFACQgGACgGAAQgHAAgFgCgAgYgIIgEACIgDAFIgBADIAAAFIABAEIADAEIAEADIAGABIAGgBIAEgDIADgEIAAgEIAAgFIAAgDIgDgFIgEgCIgGgBIgGABgAgzgaIAAgOIAZAAIAAgOIAQAAIAAAOIAXAAIAAAOg");
	this.shape_1.setTransform(5.6,-0.5);

	this.shape_2 = new cjs.Shape();
	this.shape_2.graphics.f("#000000").s().p("AAkA4IAAhvIAOAAIAABvgAANAzIAAg5IgNAAIAAgMIANAAIAAgjIAPAAIAABogAgxATIAIgIIAIgJIADgEIACgEIACgFIAAgGIAAgOIgSAAIAAgNIAxAAIAAANIgQAAIAAAOIAAAGIABAEIADAEIACADIAHAJIAJAJIgKAJIgGgIIgHgHIgDgEIgDgEIgDAEIgDAFIgIAIIgHAIg");
	this.shape_2.setTransform(-5.7,-0.5);

	this.shape_3 = new cjs.Shape();
	this.shape_3.graphics.f("#000000").s().p("AgZA5IAAghIA2AAIAAgIIg3AAIAAgMIBGAAIAAAgIg2AAIAAAIIA5AAIAAANgAgugHIAUgIQAIgDAEgDQAEgDABgDIABgHIAAgEIgiAAIAAgNIAwAAIAAASQAAAHgCAFQgCAFgFAEQgFAEgJAEIgYAIgAAdAAIAAgJIgTAAIAAgNIATAAIAAgJIgRAAIAAgNIARAAIAAgMIAPAAIAAA4g");
	this.shape_3.setTransform(-16.5,-0.4);

	this.shape_4 = new cjs.Shape();
	this.shape_4.graphics.f("#F5F5F5").s().p("AqECWQgVAAgPgPQgOgPAAgUIAAjHQAAgUAOgPQAPgPAVAAIUJAAQAUAAAPAPQAPAPAAAUIAADHQAAAUgPAPQgPAPgUAAg");

	this.timeline.addTween(cjs.Tween.get({}).to({state:[{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]}).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-69.5,-15,139,30);


(lib.Tween19 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#FFFFFF").s().p("AjDCxIAAgxIBcAAIAAhgQgUgPgLgTQgGgKgDgLQgDgLAAgNIAAgUQAAgZALgUQALgUAUgOQAUgOAbgIQAbgIAeAAQAfAAAbAIQAbAIAUAOQATAOAMAUQALAUAAAZIAAAUQAAAZgLAUQgMATgUAPIAABgIBcAAIAAAxgAguCAIBdAAIAAhJQgXAGgYgBQgXABgXgGgAghh6QgRAEgMAIQgMAIgHALQgIANAAANIAAAQQAAAOAIALQAHALAMAJQANAHAQAEQAQAFARAAQASAAAQgFQARgEAMgHQAMgJAHgLQAIgLAAgOIAAgQQAAgNgIgNQgHgLgMgIQgMgIgRgEQgQgFgSAAQgRAAgQAFg");
	this.shape.setTransform(220.9,27.3);

	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#FFFFFF").s().p("ACKDWIAAmrIA3AAIAAGrgAAyDBIAAjfIg4AAIAAgzIA4AAIAAh8IA4AAIAAGOgAjABHIAfgfIAdgiIAOgTIAJgSIAEgVIABgYIAAhnIA6AAIAABnIABAXIAGAUIAIARIANARIAbAhIAfAhIgmAjIgyg4QgUgXgEgIIAAAAIgJAOIgPAUIgLAMIgOAQIgQARIgQAPg");
	this.shape_1.setTransform(177.5,29);

	this.shape_2 = new cjs.Shape();
	this.shape_2.graphics.f("#FFFFFF").s().p("ABRDWIAAmrIA7AAIAACmIA6AAIAAAxIg6AAIAADUgAh1BxQgUgIgNgNQgOgMgHgRQgHgPAAgRIAAgcQAAgQAHgQQAHgQAOgNQANgNAUgIQATgHAaAAQAZAAAUAHQAUAIANANQANANAHAQQAHAQAAAQIAAAcQAAARgHAPQgHARgNAMQgNANgUAIQgUAHgZABQgagBgTgHgAhegiQgKAFgGAGQgHAHgDAHQgEAJAAAHIAAATQAAAIAEAJQADAIAHAHQAGAGAKAEQAKADAMAAQAMAAAKgDQAKgEAHgGQAGgHAEgIQADgJAAgIIAAgTQAAgHgDgJQgEgHgGgHQgHgGgKgFQgKgDgMAAQgMAAgKADgAjFhqIAAgwIBgAAIAAg4IA7AAIAAA4IBaAAIAAAwg");
	this.shape_2.setTransform(137.6,29);

	this.shape_3 = new cjs.Shape();
	this.shape_3.graphics.f("#FFFFFF").s().p("AB8DWIAAjjIgkAAIAADOIg4AAIAAmOIA4AAIAACNIAkAAIAAiVIA3AAIAAGrgAizBqIAAinIBuAAIAAhDIhuAAIAAgxICmAAIAACiIhvAAIAABIIATAAIA4gCIA4gFIAHAwQgbAEghACIhHACg");
	this.shape_3.setTransform(95.1,29);

	this.shape_4 = new cjs.Shape();
	this.shape_4.graphics.f("#FFFFFF").s().p("ABzDWIAAjnIhdAAQgHARgLAPQgLAPgUARQgTARgcASIhDArIgfguIA7gkQAYgQAQgOQARgNAJgMQAKgMAEgNQAFgMACgOIABgeIAAgfIiLAAIAAgyIDEAAIAABTIAAAMIgBANIBUAAIAAiTIA6AAIAAGrg");
	this.shape_4.setTransform(52.7,29);

	this.shape_5 = new cjs.Shape();
	this.shape_5.graphics.f("#FFFFFF").s().p("AjDCsIAAgxICnAAIAAg6IhyAAIAAiOIDhAAIAAgtIjjAAIAAgxIEdAAIAACPIjhAAIAAAtIDrAAIAAAwIh6AAIAAA6ICnAAIAAAxg");
	this.shape_5.setTransform(0.1,27.8);

	this.shape_6 = new cjs.Shape();
	this.shape_6.graphics.f("#FFFFFF").s().p("AiNDYIAAhuIDiAAIAAgZIjkAAIAAgsIAyAAIAAghIhmAAIAAgrIGHAAIAAArIhmAAIAAAhIAvAAIAABuIjiAAIAAAZIDvAAIAAAsgAgkAlIBJAAIAAghIhJAAgAg4g/QgagFgSgJQgTgJgKgOQgKgOAAgRIAAgLQAAgRAKgNQAKgOATgKQASgJAagFQAZgFAfAAQAgAAAaAFQAZAFASAJQATAKAKAOQAKANAAARIAAALQAAARgKAOQgKAOgTAJQgSAJgZAFQgaAGgggBQgfABgZgGgAgminQgPADgKAFQgKADgEAGQgEAGAAAEIAAAHQAAAFAEAFQAEAGAKADQAKAEAPAEQAQACAWAAQAXAAAPgCQAQgEAKgEQAKgDAEgGQAEgFAAgFIAAgHQAAgEgEgGQgEgGgKgDQgKgFgQgDQgPgCgXAAQgWAAgQACg");
	this.shape_6.setTransform(-41.7,29.1);

	this.shape_7 = new cjs.Shape();
	this.shape_7.graphics.f("#FFFFFF").s().p("AiHDWIAAhzIA7AAIAABCIDnAAIAAAxgABWByIAAlDIA5AAIAACFIA5AAIAAAyIg5AAIAACMgAjHBVIAAgwIA5AAIAxgBIAAgVQgRgDgNgGQgNgFgKgIQgSgPAAgXIAAgLQAAgNAHgLQAGgMANgIQANgJASgEQATgFAYAAQAYAAASAFQATAEAMAJQAMAIAHAMQAHALAAANIAAALQAAAWgSAQQgJAIgNAFQgNAGgRADIAAAUIA3gBIAtgDIAEAvIgsADIg4ADIhKABgAhihDQgLAHAAAIIAAADQAAAJALAGQAMAIAWAAQAWAAALgIQAMgGAAgJIAAgDQAAgIgMgHQgLgIgWAAQgWAAgMAIgAi2iBIAAgwIBZAAIAAglIA6AAIAAAlIBVAAIAAAwg");
	this.shape_7.setTransform(-83.2,28.6);

	this.shape_8 = new cjs.Shape();
	this.shape_8.graphics.f("#FFFFFF").s().p("Ah+DUIAAiOIA7AAIAABcIDZAAIAAAygABSBdIAAkvIA6AAIAAByIA4AAIAAAyIg4AAIAACLgAjDAOIBOgjQAdgPAQgNQAQgOAEgNQAFgOAAgTIAAgaIiBAAIAAgxIC6AAIAABRQAAAbgGAUQgEAKgGAJQgHAKgJAJQgUASgkASQgkATg5AYg");
	this.shape_8.setTransform(-136.5,28.7);

	this.shape_9 = new cjs.Shape();
	this.shape_9.graphics.f("#FFFFFF").s().p("AB4DWIAAmrIA7AAIAAGrgAiyBJQAngjAdggIARgVIAMgVIAFgVIACgaIAAhkIA7AAIAABkIABAZQACAKAEALIAIATQAHAKAKAKIAgAhIAjAiIglAnIgegdIgagcIgXgZQgJgKgCgGIgBAAQgDAGgKAMIgWAZIgbAbIgiAhg");
	this.shape_9.setTransform(-180.2,29);

	this.shape_10 = new cjs.Shape();
	this.shape_10.graphics.f("#FFFFFF").s().p("AhhDYIAAh6IDTAAIAAghIjWAAIAAguIEOAAIAAB7IjTAAIAAAgIDgAAIAAAugAi2gjIAlgbIAfgZQASgQAHgQQAIgQAAgUIAAgxIA6AAIAAAwIACATIAFARIAKAPIAPAOIANAJIAQAMIARANIASALIghAoIgdgUIgcgVIgWgSQgIgHgCgFIgBAAQgCAFgJAJIgUASIgfAZIgkAbgABwgDIAAjUIA6AAIAADUg");
	this.shape_10.setTransform(-221.6,29.2);

	this.shape_11 = new cjs.Shape();
	this.shape_11.graphics.f("#FFFFFF").s().p("AjAChIAAgcICTAAIAAigIAhAAIAACgIDNAAIAAAcgABVBLIAHgqIAFgoIADgqIACgsIAAgmIj1AAIAAgdIEWAAIAAAyIgBA2IgDAwIgFArIgHAsg");
	this.shape_11.setTransform(220.9,-32.1);

	this.shape_12 = new cjs.Shape();
	this.shape_12.graphics.f("#FFFFFF").s().p("ABfDSIAAmjIAhAAIAACsIBGAAIAAAdIhGAAIAADagAh1BqQgSgHgNgNQgMgMgHgPQgHgQAAgQIAAgZQAAgPAHgQQAHgPAMgNQANgMASgIQATgHAYAAQAXAAATAHQASAIANAMQAMANAGAPQAHAQAAAPIAAAZQAAAQgHAQQgGAPgMAMQgNANgSAHQgTAIgXAAQgYAAgTgIgAhngzQgMAFgJAIQgJAIgFAMQgFALAAAMIAAATQAAANAFALQAFALAJAJQAJAIAMAFQANAEAQAAQAPAAANgEQANgFAIgIQAJgJAFgLQAFgLAAgNIAAgTQAAgMgFgLQgFgMgJgIQgIgIgNgFQgNgEgPAAQgQAAgNAEgAjFhyIAAgdIBqAAIAAhAIAhAAIAABAIBoAAIAAAdg");
	this.shape_12.setTransform(179.7,-31.1);

	this.shape_13 = new cjs.Shape();
	this.shape_13.graphics.f("#FFFFFF").s().p("AhcDSIAAi0IAgAAIAAA6IDAAAIAAg6IAgAAIAAC0gAg8C1IDAAAIAAhBIjAAAgACEAFIAAjWIAgAAIAADWgAhmgLQgTgHgOgMQgNgLgHgQQgIgOAAgQIAAgXQAAgPAIgPQAHgPANgMQAOgMATgHQATgHAXAAQAXAAATAHQASAHANAMQAOAMAHAPQAHAPAAAPIAAAXQAAAQgHAOQgHAQgOALQgNAMgSAHQgTAHgXABQgXgBgTgHgAhZifQgNAEgJAIQgKAJgFAKQgGAKAAALIAAAQQAAAMAGAKQAFAKAKAJQAJAHANAFQAOAFAPABQAPgBANgFQANgFAKgHQAJgJAFgKQAFgKAAgMIAAgQQAAgLgFgKQgFgKgJgJQgKgIgNgEQgNgGgPAAQgPAAgOAGg");
	this.shape_13.setTransform(136.2,-31.1);

	this.shape_14 = new cjs.Shape();
	this.shape_14.graphics.f("#FFFFFF").s().p("ABYDSIAAmjIAhAAIAACgIBGAAIAAAdIhGAAIAADmgAi+BYIA8glQAagQARgOQARgOALgLQALgNAFgNQAGgMABgOIACggIAAg0IiYAAIAAgdIC4AAIAABSQAAAfgGAXQgDAMgGALQgGAKgIALQgSAWgiAYQgiAYg4Ahg");
	this.shape_14.setTransform(96.4,-31.1);

	this.shape_15 = new cjs.Shape();
	this.shape_15.graphics.f("#FFFFFF").s().p("AhfDWIAAi8IA6AAIAAAwICcAAIAAgwIA6AAIAAC8gAglCmICcAAIAAgtIicAAgAiwgaIAmgcIAfgaQASgPAHgQQAIgQAAgVIAAgxIA6AAIAAAwIACAUQABAJAEAHIAJAPIAQAPIAeAVIAiAZIggAmIg6goQgcgUgEgKIgBAAQgCAGgJAJIgUASIgfAZIgmAcgAB3AGIAAjbIA6AAIAADbg");
	this.shape_15.setTransform(40.3,-31.1);

	this.shape_16 = new cjs.Shape();
	this.shape_16.graphics.f("#FFFFFF").s().p("ABvDWIAAjnIhJAAIAAB4IjPAAIAAkaIA6AAIAABgIBcAAIAAhgIA5AAIAABvIBJAAIAAiRIA6AAIAAGrgAhvA2IBcAAIAAhXIhcAAg");
	this.shape_16.setTransform(-0.3,-31.1);

	this.shape_17 = new cjs.Shape();
	this.shape_17.graphics.f("#FFFFFF").s().p("AhkDWIAAimIEVAAIAACmgAgqClIChAAIAAhFIihAAgAB6AcIAAjxIA3AAIAADxgAAmAbIAAhhIgpAAIAABIIisAAIAAi+ICsAAIAABFIApAAIAAhYIA3AAIAADqgAh5gsIA/AAIAAhhIg/AAg");
	this.shape_17.setTransform(-42.5,-31.1);

	this.shape_18 = new cjs.Shape();
	this.shape_18.graphics.f("#FFFFFF").s().p("AjDCpIAAgyIGHAAIAAAygAipARIA1gZIAtgbQAYgOAJgPQAKgPAAgWIAAgSIh3AAIAAgxIEnAAIAAAxIh2AAIAAASQAAAWAJAPQAJAPAYAOIAuAbIA0AZIgaAtIgZgMIgagOIgbgPIgYgOIgcgRQgJgIgDgHIgBAAQgBAHgLAIIgdASIgYANIgbAPIgbAPIgYAMg");
	this.shape_18.setTransform(-95.1,-32);

	this.shape_19 = new cjs.Shape();
	this.shape_19.graphics.f("#FFFFFF").s().p("ABvDWIAAmrIA6AAIAAGrgAhVBuQgNgBgKgGQgVgIgOgRQgNgRgGgWQgGgXAAgaIAAgzQAAgaAGgXQAGgXANgRQAOgQAVgKQAVgIAdgBQAdABAVAIQAUAKAOAQQANARAGAXQAHAXAAAaIAAAzQAAAagHAXQgGAWgNARQgOARgUAIQgKAGgNABIgbADgAhkhxQgGAKgDAPQgEAPAAATIAAAlQAAATAEAPQADAOAGALQAGAKALAFQAKAFAPAAQAPAAAKgFQALgFAGgKQANgVAAgmIAAglQAAgngNgUQgNgVgdAAQgdAAgNAVg");
	this.shape_19.setTransform(-137.4,-31.1);

	this.shape_20 = new cjs.Shape();
	this.shape_20.graphics.f("#FFFFFF").s().p("ACFDWIAAmrIA4AAIAAGrgAAyDKIAAhXIhBAAIAAgwIBBAAIAAkRIA3AAIAAGYgAhmC7IAAiMIhWAAIAAgvIBDgBIA3gBIAxgCIAxgCIADAwIgnACIgrACIAACNgAh3gfQgTgGgMgMQgNgLgGgOQgGgOAAgOIAAgRQAAgOAGgOQAGgOANgLQAMgMATgGQASgIAYABQAZgBASAIQATAGAMAMQALALAHAOQAGAOAAAOIAAARQAAAOgGAOQgHAOgLALQgMAMgTAGQgSAIgZgBQgYABgSgIgAhhiVQgJAFgFAFQgFAHgDAGIgCAMIAAAHIACAMQADAGAFAHQAFAFAJAFQAJADALAAQAMAAAJgDQAIgFAGgFQAFgHACgGIADgMIAAgHIgDgMQgCgGgFgHQgGgFgIgFQgJgDgMAAQgLAAgJADg");
	this.shape_20.setTransform(-179.5,-31.1);

	this.timeline.addTween(cjs.Tween.get({}).to({state:[{t:this.shape_20},{t:this.shape_19},{t:this.shape_18},{t:this.shape_17},{t:this.shape_16},{t:this.shape_15},{t:this.shape_14},{t:this.shape_13},{t:this.shape_12},{t:this.shape_11},{t:this.shape_10},{t:this.shape_9},{t:this.shape_8},{t:this.shape_7},{t:this.shape_6},{t:this.shape_5},{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]}).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-243.7,-57.4,487.6,114.9);


(lib.Path_1 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.lf(["rgba(0,0,58,0)","#00003A"],[0,1],32.4,47.3,330.8,483.8).s().p("EhHeAu4MCO9hdvMAAABdvg");
	this.shape.setTransform(457.5,300);

	this.timeline.addTween(cjs.Tween.get(this.shape).wait(1));

}).prototype = getMCSymbolPrototype(lib.Path_1, new cjs.Rectangle(0,0,915.1,600), null);


(lib.Group = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f().s("#FFFFFF").p("AgJAAIATAA");
	this.shape.setTransform(167,0.5);

	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#FFFFFF").s().p("ALkAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAAAgBQgBAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAABAAQAAgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAABABQAAAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAAAAAQgBABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAKUAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAAAgBQgBAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAABAAQAAgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAAAABQABAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAgBAAQAAABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAJEAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAAAgBQgBAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAABAAQAAgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAAAABQABAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAgBAAQAAABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAH0AFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAgBgBQAAAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAAAAAQABgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAAAABQABAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAgBAAQAAABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAGkAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAgBgBQAAAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAAAAAQABgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAAAABQABAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAgBAAQAAABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAFUAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAgBgBQAAAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAAAAAQABgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAAAABQABAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAgBAAQAAABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAEEAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAgBgBQAAAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAAAAAQABgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAABABQAAAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAAAAAQgBABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAC0AFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAgBgBQAAAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAAAAAQABgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAABABQAAAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAAAAAQgBABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgABkAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAAAgBQgBAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAABAAQAAgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAABABQAAAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAAAAAQgBABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAAUAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAAAgBQgBAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAABAAQAAgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAABABQAAAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAAAAAQgBABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAg7AFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAAAgBQgBAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAABAAQAAgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAAAABQABAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAgBAAQAAABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAiLAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAAAgBQgBAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAABAAQAAgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAAAABQABAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAgBAAQAAABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAjbAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAgBgBQAAAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAAAAAQABgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAAAABQABAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAgBAAQAAABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAkrAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAgBgBQAAAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAAAAAQABgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAAAABQABAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAgBAAQAAABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAl7AFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAgBgBQAAAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAAAAAQABgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAAAABQABAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAgBAAQAAABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAnLAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAgBgBQAAAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAAAAAQABgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAABABQAAAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAAAAAQgBABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAobAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAgBgBQAAAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAAAAAQABgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAABABQAAAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAAAAAQgBABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAprAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAAAgBQgBAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAABAAQAAgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAABABQAAAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAAAAAQgBABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAq7AFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAAAgBQgBAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAABAAQAAgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAABABQAAAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAAAAAQgBABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAgAsLAFQgBAAAAAAQAAAAgBAAQAAgBgBAAQAAAAAAgBQgBAAAAAAQAAgBgBAAQAAgBAAAAQAAAAAAgBQAAAAAAAAQAAAAAAgBQABAAAAgBQAAAAABAAQAAgBAAAAQABAAAAgBQABAAAAAAQAAAAABAAIAoAAQABAAAAAAQAAAAABAAQAAABABAAQAAAAAAABQABAAAAAAQAAABABAAQAAABAAAAQAAAAAAAAQAAABAAAAQAAAAAAABQgBAAAAABQAAAAgBAAQAAABAAAAQgBAAAAABQgBAAAAAAQAAAAgBAAg");
	this.shape_1.setTransform(84,0.5);

	this.shape_2 = new cjs.Shape();
	this.shape_2.graphics.f().s("#FFFFFF").p("AgJAAIATAA");
	this.shape_2.setTransform(1,0.5);

	this.timeline.addTween(cjs.Tween.get({}).to({state:[{t:this.shape_2},{t:this.shape_1},{t:this.shape}]}).wait(1));

}).prototype = getMCSymbolPrototype(lib.Group, new cjs.Rectangle(-1,-0.5,170,2), null);


(lib.Path = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#FF0C00").s().p("EggMALUIAA2nMA+8AAAQAxAAAaAjQAaAjgNAxIk4S5QgNAxgsAjQgsAjgxAAg");
	this.shape.setTransform(206.1,72.4);

	this.timeline.addTween(cjs.Tween.get(this.shape).wait(1));

}).prototype = getMCSymbolPrototype(lib.Path, new cjs.Rectangle(0,0,412.2,144.8), null);


(lib.Path_1_1 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#00003A").s().p("Eg02ANnIAA7VMBn8AAAQA8ABAfAqQAgAqgQA7Il7W8QgPA7g1ArQg2Aqg7AAg");
	this.shape_1.setTransform(338.3,87.9);

	this.timeline.addTween(cjs.Tween.get(this.shape_1).wait(1));

}).prototype = getMCSymbolPrototype(lib.Path_1_1, new cjs.Rectangle(0,0,676.5,175.7), null);


(lib.Tween15 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#FFFFFF").s().p("AhVEUIg5gNQg2gRgrguIBQhcQAdAcAjAOQAjANAkAAQAcAAASgGQATgGALgJQALgJAEgJIADgQQAAgMgHgHQgIgJgOgHIgjgOIgvgPQgcgJgXgMQgXgMgRgQQgSgQgJgXQgKgXAAgfIAFgqQAEgTAJgRQARgiAegXQAdgWAmgMQAmgMAqAAIA0ABIAyAIQAZAEAYALQAXAJAXASIg8BoQgigZghgIQghgJggABIgfACIgbAJQgNAGgIALQgHAKAAARQAAAXAZAOQAYAOA5AOQArALAaAPQAbAPAQASQAPASAGAXQAFAWAAAYQAAAVgEASQgFASgKARQgSAhggAXQggAXgqANQgqANgvAAg");
	this.shape.setTransform(104.4,-0.8);

	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#FFFFFF").s().p("AjvERIBrohIF0AAIgWBxIj3AAIgTBnIDZAAIgWBuIjZAAIgVBsIEHAAIgWBvg");
	this.shape_1.setTransform(58.9,-0.6);

	this.shape_2 = new cjs.Shape();
	this.shape_2.graphics.f("#FFFFFF").s().p("AicERIBUmwIiLAAIAWhxIGRAAIgVBxIiKAAIhTGwg");
	this.shape_2.setTransform(19.1,-0.6);

	this.shape_3 = new cjs.Shape();
	this.shape_3.graphics.f("#FFFFFF").s().p("AB7ERIgMhsIivAAIg1BsIiKAAIEpohICCAAIBUIhgABjA+IgEg7IgOiAIgEAAIg3ByIgiBJIBvAAg");
	this.shape_3.setTransform(-33.8,-0.6);

	this.shape_4 = new cjs.Shape();
	this.shape_4.graphics.f("#FFFFFF").s().p("AAsERIgrjHIhPAAIgmDHIh+AAIBqohIDwAAQAkAAAaAMQAaAMARAUQARAUAIAZQAJAagBAbQABAWgGAZQgFAagNAZQgNAZgXAWQgXAXgjAQIA2DbgAg3glIBgAAQATAAAOgGQAPgGAKgLQAVgXAAgeQAAgUgNgNQgOgNgdAAIhgAAg");
	this.shape_4.setTransform(-78.7,-0.6);

	this.instance = new lib.Path();
	this.instance.parent = this;
	this.instance.setTransform(0.1,0.1,1,1,0,0,0,206.1,72.4);
	this.instance.shadow = new cjs.Shadow("rgba(0,0,0,0.2)",12,12,0);

	this.timeline.addTween(cjs.Tween.get({}).to({state:[{t:this.instance},{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]}).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-206,-72.3,430,162);


(lib.Tween14 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#FFFFFF").s().p("AhVEUIg5gNQg2gRgrguIBQhcQAdAcAjAOQAjANAkAAQAcAAASgGQATgGALgJQALgJAEgJIADgQQAAgMgHgHQgIgJgOgHIgjgOIgvgPQgcgJgXgMQgXgMgRgQQgSgQgJgXQgKgXAAgfIAFgqQAEgTAJgRQARgiAegXQAdgWAmgMQAmgMAqAAIA0ABIAyAIQAZAEAYALQAXAJAXASIg8BoQgigZghgIQghgJggABIgfACIgbAJQgNAGgIALQgHAKAAARQAAAXAZAOQAYAOA5AOQArALAaAPQAbAPAQASQAPASAGAXQAFAWAAAYQAAAVgEASQgFASgKARQgSAhggAXQggAXgqANQgqANgvAAg");
	this.shape.setTransform(104.4,-0.8);

	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#FFFFFF").s().p("AjvERIBrohIF0AAIgWBxIj3AAIgTBnIDZAAIgWBuIjZAAIgVBsIEHAAIgWBvg");
	this.shape_1.setTransform(58.8,-0.6);

	this.shape_2 = new cjs.Shape();
	this.shape_2.graphics.f("#FFFFFF").s().p("AicERIBUmwIiLAAIAWhxIGRAAIgVBxIiKAAIhTGwg");
	this.shape_2.setTransform(19,-0.6);

	this.shape_3 = new cjs.Shape();
	this.shape_3.graphics.f("#FFFFFF").s().p("AB8ERIgNhsIivAAIg1BsIiKAAIEpohICCAAIBUIhgABkA+IgGg7IgNiAIgEAAIg4ByIgiBJIBxAAg");
	this.shape_3.setTransform(-33.9,-0.6);

	this.shape_4 = new cjs.Shape();
	this.shape_4.graphics.f("#FFFFFF").s().p("AArERIgpjHIhPAAIgmDHIh+AAIBoohIDxAAQAjAAAbAMQAaAMARAUQARAUAJAZQAHAaAAAbQAAAWgEAZQgFAagNAZQgOAZgWAWQgYAXgkAQIA2DbgAg4glIBgAAQAUAAAPgGQAPgGAKgLQAUgXAAgeQAAgUgOgNQgNgNgcAAIhgAAg");
	this.shape_4.setTransform(-78.7,-0.6);

	this.instance = new lib.Path();
	this.instance.parent = this;
	this.instance.setTransform(0,0.1,1,1,0,0,0,206.1,72.4);
	this.instance.shadow = new cjs.Shadow("rgba(0,0,0,0.2)",12,12,0);

	this.timeline.addTween(cjs.Tween.get({}).to({state:[{t:this.instance},{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]}).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-206.1,-72.3,430,162);


(lib.Tween13 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#FFFFFF").s().p("AkiFLICBqWIHEAAIgZCJIktAAIgYB9IEIAAIgaCGIkIAAIgaCDIE/AAIgbCHg");
	this.shape.setTransform(216.6,-0.6);

	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#FFFFFF").s().p("AiIFEQgogOgegaQgegbgRglQgRgmgBgvIAGg1IArjRQAMg4AagoQAcgoAmgZQAlgZAsgNQAsgLAsAAIA9AEIA+AMQAfAJAZANQAZAMARASIhYB2QgagcgjgJQgigJggAAIgoAGQgUAGgTAMQgTAMgOATQgOAUgGAdIgjCmIgDAmQAAAsAYAVQAXAWApgBIAkgEQAUgFATgNIARhXIhbAAIAaiGID0AAIg4EuQgfAaggAQQggAQgfAJQggAJgdAEIg3ACQgsAAgogOg");
	this.shape_1.setTransform(158.1,-0.6);

	this.shape_2 = new cjs.Shape();
	this.shape_2.graphics.f("#FFFFFF").s().p("ABKFLIhijJIhTiyIgEAAIhJF7IiZAAIB/qWICKAAIBPChIBmDcIADAAIBKl9ICYAAIiAKWg");
	this.shape_2.setTransform(94.3,-0.6);

	this.shape_3 = new cjs.Shape();
	this.shape_3.graphics.f("#FFFFFF").s().p("ACWFLIgPiCIjUAAIhBCCIioAAIFpqWICeAAIBmKWgAB5BMIgGhIIgRicIgDAAIhFCKIgpBaICIAAg");
	this.shape_3.setTransform(22.8,-0.6);

	this.shape_4 = new cjs.Shape();
	this.shape_4.graphics.f("#FFFFFF").s().p("AAoFLIA1kMIjUAAIgzEMIiZAAICAqWICaAAIgyEBIDSAAIAykBICbAAIiBKWg");
	this.shape_4.setTransform(-32.2,-0.6);

	this.shape_5 = new cjs.Shape();
	this.shape_5.graphics.f("#FFFFFF").s().p("AhzFGQgogMghgZQghgZgWgmQgVgnAAg1IAGg1IAojMQAMg2AXgpQAXgoAigbQBGg0BtAAIA8AFIA7AOIA0AYQAZAMAQASIhWB0QgagbgjgLQgjgKggAAQgzAAgfAbQggAcgLA5IggCyIgBALIgBALQAAAVAJAQQAJAOANAKQAOAKASAEIAjADIAggDIAlgIIAlgPIAhgUIBBB+QgeAUgeANIg8AUIg6AIIg2ACQglAAgogLg");
	this.shape_5.setTransform(-89.5,-0.7);

	this.shape_6 = new cjs.Shape();
	this.shape_6.graphics.f("#FFFFFF").s().p("ABGFLIhPjaIihDaIi2AAIEXlTIiKlDICoAAIBGDJICRjJIC1AAIkIFDICdFTg");
	this.shape_6.setTransform(-148.7,-0.6);

	this.shape_7 = new cjs.Shape();
	this.shape_7.graphics.f("#FFFFFF").s().p("AkiFLICBqWIHEAAIgZCJIktAAIgYB9IEIAAIgaCGIkIAAIgaCDIE/AAIgbCHg");
	this.shape_7.setTransform(-206.5,-0.6);

	this.instance = new lib.Path_1_1();
	this.instance.parent = this;
	this.instance.setTransform(0,0.1,1,1,0,0,0,338.2,87.9);
	this.instance.alpha = 0.949;
	this.instance.shadow = new cjs.Shadow("rgba(0,0,0,0.2)",12,12,0);

	this.timeline.addTween(cjs.Tween.get({}).to({state:[{t:this.instance},{t:this.shape_7},{t:this.shape_6},{t:this.shape_5},{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]}).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-338.2,-87.8,694,192);


(lib.Path_3 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.instance = new lib.Group();
	this.instance.parent = this;
	this.instance.setTransform(84,0.5,1,1,0,0,0,84,0.5);
	this.instance.alpha = 0.398;

	this.timeline.addTween(cjs.Tween.get(this.instance).wait(1));

}).prototype = getMCSymbolPrototype(lib.Path_3, new cjs.Rectangle(-0.5,0,169,1), null);


(lib.Tween23 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#FFFFFF").s().p("AAcApIAAgYIguAAIAAgGIA0AAIAAAegAAcAHIAAgWIgMAAIAAAVIgGAAIAAguIAGAAIAAATIAMAAIAAgUIAGAAIAAAwgAgXAAIgGgEIgDgFIgBgHIAAgEIABgGIADgGIAGgDQAEgCAEAAQAEAAADACIAGADIADAGIABAGIAAAEIgBAHIgDAFIgGAEQgDAAgEABQgEgBgEAAgAgUgfIgEADIgCAEIgBAFIAAADIABAEIACAFIAEACIAFABIAFgBIADgCIADgFIABgEIAAgDIgBgFIgDgEIgDgDIgFgBIgFABg");
	this.shape.setTransform(-41.2,2.1);

	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#FFFFFF").s().p("AgaAnIAAgfIA1AAIAAAfgAgTAhIAnAAIAAgTIgnAAgAglgCIAAgFIA3AAIABgLIABgJIAAgFIgvAAIAAgGIA1AAIAAALQAAAKgCAKIAOAAIAAAFg");
	this.shape_1.setTransform(-49.1,2.3);

	this.shape_2 = new cjs.Shape();
	this.shape_2.graphics.f("#FFFFFF").s().p("AAdApIAAhRIAHAAIAABRgAAOAlIAAgsIgOAAIAAgFIAOAAIAAgbIAGAAIAABMgAgjAQIAEgEIAEgEIADgDIADgDIACgDIABgEIABgEIAAgFIAAgNIgPAAIAAgFIAjAAIAAAFIgNAAIAAANIAAAFIABAEIABAEIADADIAFAGIAHAHIgFAEIgCgDIgCgDIgEgEIgCgDIgDgEIgCgCIgCACIgDAEIgFAHIgHAHg");
	this.shape_2.setTransform(-57.5,2.1);

	this.shape_3 = new cjs.Shape();
	this.shape_3.graphics.f("#FFFFFF").s().p("AgSApIAAgVIArAAIAAgKIgrAAIAAgFIAxAAIAAAVIgrAAIAAAKIAtAAIAAAFgAghgCIASgHQAFgDAEgDQADgCAAgEIABgHIAAgDIgcAAIAAgGIAhAAIAAAJIAAAHQAAADgBACIgEAFIgGAEIgJAFIgNAEgAAZAAIAAgJIgRAAIAAgFIARAAIAAgLIgQAAIAAgFIAQAAIAAgKIAGAAIAAAog");
	this.shape_3.setTransform(-65.5,2.1);

	this.shape_4 = new cjs.Shape();
	this.shape_4.graphics.f("#FFFFFF").s().p("AgeA8IAAghIAQAAIAAATIBBAAIAAAOgAAhAhIAAgJIgYAAIAAgNIAYAAIAAhGIAQAAIAABcgAgVAWIAAgPIgOAAIgPAAIAAgNIAaAAIAVgBIAPAAIAOgBIABAMIgPACIgSABIAAAPgAgYgNQgFgBgFgCQgEgEgCgEQgCgDAAgFIAAgEQAAgEACgEQACgFAEgCQAFgDAFgBQAGgCAGAAQAHAAAFACQAFABAEADQAEACACAFQACAEABAEIAAAEQgBAFgCADQgCAEgEAEQgEACgFABQgFACgHAAQgGAAgGgCgAgXgpQgEADAAADIAAACQAAAEAEACQAFADAGAAQAHAAAEgDQADgCAAgEIAAgCQAAgDgDgDQgEgDgHABQgGgBgFADg");
	this.shape_4.setTransform(63.5,0.8);

	this.shape_5 = new cjs.Shape();
	this.shape_5.graphics.f("#FFFFFF").s().p("AgXBNQgLgEgIgJQgPgSAAgiIAAgXQAAgRAEgNQADgNAIgJQAPgSAbAAQAcAAAPASQAPASAAAiIAAAXQAAAigOASQgOASgdAAQgNAAgLgFgAgKg0QgFABgEAFQgFAEgCAJQgDAIAAANIAAAZQAAANADAJQADAIAEAFQAEAEAGACIAKABQAGAAAFgBQAFgCAEgEQAEgFACgIQADgJAAgNIAAgZQAAgNgDgJQgCgIgEgEQgEgFgGgBIgLgCIgKACg");
	this.shape_5.setTransform(42.7,-0.4);

	this.shape_6 = new cjs.Shape();
	this.shape_6.graphics.f("#FFFFFF").s().p("AgXBNQgLgEgIgJQgPgSAAgiIAAgXQAAgRAEgNQADgNAIgJQAPgSAbAAQAcAAAPASQAPASAAAiIAAAXQAAAigOASQgOASgdAAQgNAAgLgFgAgKg0QgFABgEAFQgFAEgCAJQgDAIAAANIAAAZQAAANADAJQADAIAEAFQAEAEAGACIAKABQAGAAAFgBQAFgCAEgEQAEgFACgIQADgJAAgNIAAgZQAAgNgDgJQgCgIgEgEQgEgFgGgBIgLgCIgKACg");
	this.shape_6.setTransform(29.1,-0.4);

	this.shape_7 = new cjs.Shape();
	this.shape_7.graphics.f("#FFFFFF").s().p("AgbBPQgMgCgIgGIAKgXQAIAEAIACQAHACALAAQAGAAAGgCQAGgCAEgFQAFgFACgJQACgIAAgNQgFAHgKADQgJAEgKAAQgLAAgKgEQgJgDgHgHQgFgHgEgIQgEgJABgMIAAgEQAAgLADgKQAFgKAGgHQAIgHAKgEQALgEAMAAQAPAAALAFQAKAFAIAJQAGAIADANQADAMAAAQIAAAKIgBAXQAAAKgDAJQgEAQgJAKQgIAKgMADQgLAEgOAAQgKAAgLgDgAgLg1QgFACgEADQgEAEgCAFQgCAFAAAGIAAADQAAAOAHAGQADAEAGABQAFABAHAAQAHAAAFgBQAGgCADgEQAEgDACgFQABgFAAgGIAAgDQAAgNgHgHQgDgDgGgCQgEgCgIAAQgFAAgGACg");
	this.shape_7.setTransform(15.4,-0.4);

	this.shape_8 = new cjs.Shape();
	this.shape_8.graphics.f("#FFFFFF").s().p("AgTAaIAJgzIAeAAIgSAzg");
	this.shape_8.setTransform(4.7,8.3);

	this.shape_9 = new cjs.Shape();
	this.shape_9.graphics.f("#FFFFFF").s().p("AgcBPQgKgCgKgGIALgXQAIAEAHACQAIACAKAAQAHAAAGgCQAGgCAFgFQADgFACgJQADgIABgNQgGAHgKADQgKAEgJAAQgMAAgIgEQgKgDgGgHQgHgHgDgIQgEgJAAgMIAAgEQAAgLAFgKQADgKAHgHQAIgHAKgEQAKgEANAAQAPAAALAFQALAFAGAJQAHAIAEANQADAMAAAQIAAAKIgBAXQgBAKgDAJQgEAQgJAKQgIAKgLADQgMAEgNAAQgLAAgMgDgAgLg1QgFACgFADQgDAEgCAFQgCAFAAAGIAAADQAAAOAHAGQADAEAGABQAFABAHAAQAHAAAFgBQAGgCADgEQAEgDACgFQACgFAAgGIAAgDQAAgNgIgHQgEgDgFgCQgEgCgHAAQgHAAgFACg");
	this.shape_9.setTransform(-5.7,-0.4);

	this.shape_10 = new cjs.Shape();
	this.shape_10.graphics.f().s("#FFFFFF").p("Aq2AAIVtAA");
	this.shape_10.setTransform(-0.5,-25);

	this.shape_11 = new cjs.Shape();
	this.shape_11.graphics.f("#FFFFFF").s().p("AgXApIAAgbIAGAAIAAAVIAsAAIAAAGgAATASIAAg6IAGAAIAAAYIAMAAIAAAGIgMAAIAAAcgAgkAFIAMgFIAIgFIAGgEIADgEIABgEIABgFIAAgHIgdAAIAAgFIAiAAIAAAMIAAAHIgBAFIgEAFIgGAFIgKAEIgNAGg");
	this.shape_11.setTransform(-40.8,-38);

	this.shape_12 = new cjs.Shape();
	this.shape_12.graphics.f("#FFFFFF").s().p("AAZApIAAhRIAHAAIAABRgAgfARIARgKQAGgEADgDQADgEABgEIABgIIAAgLIgeAAIAAgGIAjAAIAAARIAAAHIgCAGIgDAFIgGAGIgJAHIgNAHg");
	this.shape_12.setTransform(-49.3,-37.9);

	this.shape_13 = new cjs.Shape();
	this.shape_13.graphics.f("#FFFFFF").s().p("AglAkIAAgGIAWAAIAAgQQgFgCgBgDQgDgCAAgEIAAgDQAAgDACgDIAFgEIAIgDIAJgBIAJABIAIADIAGAEQACADAAADIAAADQAAAEgCACQgDADgEACIAAAQIAWAAIAAAGgAgJAeIATAAIAAgOIgKABIgJgBgAgGgIIgGACIgFADIgBADIAAACIABAEIAFADIAGACIAGABIAHgBIAHgCIADgDIACgEIAAgCIgCgDIgDgDIgHgCIgHgBIgGABgAgegTIAAgFIAcAAIAAgLIAFAAIAAALIAcAAIAAAFg");
	this.shape_13.setTransform(-57.2,-38.6);

	this.shape_14 = new cjs.Shape();
	this.shape_14.graphics.f("#FFFFFF").s().p("AALAoIAAgcIgVAAIAAAcIgGAAIAAgcIgVAAIAAgFIBLAAIAAAFIgUAAIAAAcgAgJgCIgIgEQgFgCgCgDQgCgDAAgEIAAgEQAAgEACgDQACgEAFgCQAEgCAEgBIAJgBIAKABQAFABAEACQADACADAEQACADAAAEIAAAEQAAAEgCADQgDADgDACIgJAEIgKABIgJgBgAgHghIgHADIgEAEQgCABAAADIAAAEQAAACACACIAEAEIAHACIAHABIAIgBIAGgCIAFgEIABgEIAAgEQABgDgCgBIgFgEIgGgDIgIAAIgHAAg");
	this.shape_14.setTransform(-65.4,-37.8);

	this.shape_15 = new cjs.Shape();
	this.shape_15.graphics.f("#FFFFFF").s().p("AgVAqIAAgUIArAAIAAgEIgrAAIAAgIIA2AAIAAAUIgrAAIAAAEIAuAAIAAAIgAAWAHIAAgBIgRAAIAAgHIARAAIAAgoIALAAIAAAwgAgPAHIAAgJIgKAAIgKAAIAAgJIASAAIAOAAIALgBIAJAAIABAIIgKABIgMAAIAAAKgAgRgOIgGgDQgDgBgCgDQgBgCAAgDIAAgCQAAgDABgCQACgDADgCIAGgCIAJAAIAIAAIAFACQADACACADQACACAAADIAAACQAAADgCACQgCADgDABIgFADIgIABIgJgBgAgQgfQgBAAAAABQgBAAAAABQAAAAgBABQAAAAAAAAIAAABQAAABAAAAQABABAAAAQAAABABAAQAAABABAAIAIABIAHgBQABAAAAgBQAAAAAAgBQAAAAABgBQAAAAAAgBIAAgBQAAAAAAAAQgBgBAAAAQAAgBAAAAQAAgBgBAAIgHgBIgIABg");
	this.shape_15.setTransform(65.4,-37.9);

	this.shape_16 = new cjs.Shape();
	this.shape_16.graphics.f("#FFFFFF").s().p("AAaAqIAAgtIgIAAIAAApIgLAAIAAhNIALAAIAAAbIAIAAIAAgdIAKAAIAABTgAgkAQIANgJIAHgHQADgCAAgFIABgIIAAgIIgWAAIAAgKIAhAAIAAATIgBALQgBAEgDAEIgJAJIgOALg");
	this.shape_16.setTransform(57.2,-37.9);

	this.shape_17 = new cjs.Shape();
	this.shape_17.graphics.f("#FFFFFF").s().p("AAHAyIAAhPIgWANIgJgPIAhgTIAQAAIAABkg");
	this.shape_17.setTransform(45,-37.7);

	this.shape_18 = new cjs.Shape();
	this.shape_18.graphics.f().s("#FFFFFF").p("Aq2AAIVtAA");
	this.shape_18.setTransform(-0.5,-65);

	this.shape_19 = new cjs.Shape();
	this.shape_19.graphics.f("#FFFFFF").s().p("AglAgIAAgGIAjAAIAAgVIgYAAIAAgkIA1AAIAAAGIgvAAIAAAZIAwAAIAAAFIgZAAIAAAVIAjAAIAAAGg");
	this.shape_19.setTransform(-24.6,-78.2);

	this.shape_20 = new cjs.Shape();
	this.shape_20.graphics.f("#FFFFFF").s().p("AgWApIAAgXIAGAAIAAARIAsAAIAAAGgAATAUIAAg7IAHAAIAAAZIAMAAIAAAFIgMAAIAAAdgAgVALIgGgEIgEgEIgBgFIAAgDIABgGIAEgEIAGgDQADgCAEAAQAFAAADACIAGADIADAEIABAGIAAADIgBAFIgDAEIgGAEIgIABIgHgBgAgTgNIgEACIgDADIAAADIAAADIAAACIADADIAEACIAFABIAGgBIAEgCIACgDIABgCIAAgDIgBgDIgCgDIgEgCIgGgBIgFABgAglgYIAAgGIAUAAIAAgKIAHAAIAAAKIATAAIAAAGg");
	this.shape_20.setTransform(-32.7,-78);

	this.shape_21 = new cjs.Shape();
	this.shape_21.graphics.f("#FFFFFF").s().p("AgJAoIgIgDQgDgBgCgDQgCgDAAgEIAAgCQAAgFACgCIAFgEQADgDAFAAIAJgBIAKABIAIADIAFAEQACADAAAEIAAACQAAAEgCADQgCADgDABIgIADIgKABIgJgBgAgGAPIgHABIgEADIgCAFIAAACQAAABABAAQAAABAAAAQAAABAAAAQABABAAAAIAEADIAHACIAGAAIAHAAIAGgCIAEgDQABAAAAgBQABAAAAgBQAAAAAAgBQABAAAAgBIAAgCQAAgBgBAAQAAgBAAgBQAAAAgBgBQAAAAgBgBIgEgDIgGgBIgHgBIgGABgAglABIAAgEIBLAAIAAAEgAgZgNIAAgbIAGAAIAAAVIAuAAIAAAGg");
	this.shape_21.setTransform(-40.9,-77.7);

	this.shape_22 = new cjs.Shape();
	this.shape_22.graphics.f("#FFFFFF").s().p("AARApIAAhRIAHAAIAAAfIANAAIAAAGIgNAAIAAAsgAglARIAMgHIAJgGIAFgEIADgFIACgFIAAgHIAAgKIgeAAIAAgFIAkAAIAAAQIgBAKIgBAFIgEADIgKAJIgRALg");
	this.shape_22.setTransform(-48.9,-77.9);

	this.shape_23 = new cjs.Shape();
	this.shape_23.graphics.f("#FFFFFF").s().p("AAbApIAAgtIgMAAIAAApIgGAAIAAhMIAGAAIAAAdIAMAAIAAgeIAGAAIAABRgAggAUIAAgeIAXAAIAAgSIgXAAIAAgFIAeAAIAAAdIgYAAIAAASIAGAAIAGAAIAFAAIAGgBIAGgBIAAAGIgMABIgMABg");
	this.shape_23.setTransform(-57.3,-77.9);

	this.shape_24 = new cjs.Shape();
	this.shape_24.graphics.f("#FFFFFF").s().p("AAaApIAAguIgXAAIgDAGIgFAGIgJAHIgOAIIgEgFIAMgHIAJgGIAGgEIACgFIACgFIAAgGIAAgLIgeAAIAAgFIAjAAIAAAQIAAAFIAWAAIAAgdIAGAAIAABRg");
	this.shape_24.setTransform(-65.6,-77.9);

	this.shape_25 = new cjs.Shape();
	this.shape_25.graphics.f("#FFFFFF").s().p("AgVAqIAAgXIAMAAIAAANIAsAAIAAAKgAAWAXIAAgGIgQAAIAAgJIAQAAIAAgxIAMAAIAABAgAgPAQIAAgLIgJAAIgKAAIAAgIIASgBIAOAAIAKAAIAJgBIABAIIgKABIgMABIAAALgAgQgIIgHgDIgEgFQgCgDAAgDIAAgCQAAgEACgCQABgDADgCIAHgDIAIgBQAEAAAEABQADABADACQADACABADQACACAAAEIAAACQAAADgCADIgEAFQgDACgDABIgIABIgIgBgAgPgcQgDACAAACIAAACQAAAAAAABQAAABAAAAQABAAAAABQABAAABABQACACAFAAQAFAAACgCQACgCAAgCIAAgCQAAgCgCgCQgCgCgFAAQgFAAgCACg");
	this.shape_25.setTransform(65.4,-78);

	this.shape_26 = new cjs.Shape();
	this.shape_26.graphics.f("#FFFFFF").s().p("AgZApIAAgcIAMAAIAAATIApAAIAAAJgAAOATIAAg8IAMAAIAAAXIALAAIAAAKIgLAAIAAAbgAgkAEIAAgnIAmAAIAAAngAgZgEIAQAAIAAgVIgQAAg");
	this.shape_26.setTransform(57.6,-78);

	this.shape_27 = new cjs.Shape();
	this.shape_27.graphics.f("#FFFFFF").s().p("AgPAyQgGgEgFgFQgKgMAAgVIAAgPQAAgKACgJQACgIAGgFQAKgMAQAAQASAAAKAMQAJALAAAVIAAAPQAAAVgJAMQgJALgSAAQgIAAgIgCgAgGghQgEABgCADIgEAIQgCAGAAAIIAAAPQAAAJACAFQACAGACADQADADADAAIAHABIAHgBQADAAACgDQADgEACgEQABgGAAgJIAAgPQAAgIgBgGIgFgIQgDgDgDgBIgHgBIgGABg");
	this.shape_27.setTransform(45.3,-77.7);

	this.shape_28 = new cjs.Shape();
	this.shape_28.graphics.f("#FFFFFF").s().p("AgPAyQgHgEgEgFQgKgMAAgVIAAgPQAAgKADgJQABgIAGgFQAJgMARAAQASAAAKAMQAJALAAAVIAAAPQAAAVgJAMQgJALgSAAQgIAAgIgCgAgGghQgEABgCADIgEAIQgCAGAAAIIAAAPQAAAJACAFQACAGACADQADADADAAIAHABIAHgBQADAAACgDQADgEACgEQABgGAAgJIAAgPQAAgIgBgGIgFgIQgDgDgDgBIgHgBIgGABg");
	this.shape_28.setTransform(36.7,-77.7);

	this.shape_29 = new cjs.Shape();
	this.shape_29.graphics.f("#FFFFFF").s().p("AgjAzIAAgNIAkgmIAHgHIAEgGIACgFIAAgEIAAgCQAAgEgDgDIgFgCIgGgBIgFAAIgFACIgGAFIgFAHIgNgJIAEgHIAHgHQAEgDAGgCQAGgCAHAAQAIAAAGACQAGACAEAEQAEADACAFQACAFAAAFIAAABIgBAIIgDAIQgDAHgIAHIgcAeIAvAAIAAAOg");
	this.shape_29.setTransform(27.9,-77.8);

	this.shape_30 = new cjs.Shape();
	this.shape_30.graphics.f("#FFFFFF").s().p("AgcBBIAAg5IASAAIAAAOIAuAAIAAgOIASAAIAAA5gAgKAyIAuAAIAAgNIguAAgAg1gHIAMgJIAKgIQAFgEACgFQACgFAAgGIAAgQIASAAIAAAPIABAGIABAFIACAFIAFAEIAJAHIALAHIgKALIgRgLQgIgGgCgDIgDAEIgHAGIgJAHIgLAIgAAkACIAAhCIASAAIAABCg");
	this.shape_30.setTransform(3.3,-128.5);

	this.shape_31 = new cjs.Shape();
	this.shape_31.graphics.f("#FFFFFF").s().p("AAiBBIAAhGIgXAAIAAAkIg+AAIAAhUIASAAIAAAdIAcAAIAAgdIAQAAIAAAhIAXAAIAAgsIASAAIAACBgAghARIAcAAIAAgaIgcAAg");
	this.shape_31.setTransform(-8.9,-128.5);

	this.shape_32 = new cjs.Shape();
	this.shape_32.graphics.f("#FFFFFF").s().p("AgeBBIAAgzIBUAAIAAAzgAgMAyIAwAAIAAgVIgwAAgAAlAJIAAhJIARAAIAABJgAAMAJIAAgeIgMAAIAAAWIg1AAIAAg6IA1AAIAAAWIAMAAIAAgbIAQAAIAABHgAgkgNIATAAIAAgdIgTAAg");
	this.shape_32.setTransform(-21.8,-128.5);

	this.shape_33 = new cjs.Shape();
	this.shape_33.graphics.f("#FFFFFF").s().p("Ag7A0IAAgQIB3AAIAAAQgAAkAPIgIgFIgIgEIgHgEIgJgEQgDgDAAgCIgBAAQAAACgDADIgJAEIgHAFIgIAEIgIAFIgIADIgIgOIAQgHIAOgIQAHgEADgFQADgFAAgGIAAgFIgkAAIAAgQIBZAAIAAAQIgkAAIAAAFQAAAGADAFQADAFAHAEIAOAIIAQAHIgIAOg");
	this.shape_33.setTransform(-37.7,-128.7);

	this.shape_34 = new cjs.Shape();
	this.shape_34.graphics.f("#FFFFFF").s().p("AAiBBIAAiBIARAAIAACBgAgZAiIgHgDQgGgCgFgFQgEgFgBgHQgCgHAAgHIAAgPQAAgJACgHQABgHAEgEQAFgGAGgCQAHgDAIgBQAJABAGADQAGACAEAGQAEAEACAHQACAHAAAJIAAAPQAAAHgCAHQgCAHgEAFQgEAFgGACIgHADIgIABgAgdghIgDAHIgCALIAAALIACAJIADAIQABADADACQAEABAEAAQAEAAAEgBQADgCACgDQAEgGAAgLIAAgLQAAgNgEgFQgEgHgJAAQgJAAgDAHg");
	this.shape_34.setTransform(-50.6,-128.5);

	this.shape_35 = new cjs.Shape();
	this.shape_35.graphics.f("#FFFFFF").s().p("AAoBBIAAiBIARAAIAACBgAAPA+IAAgbIgTAAIAAgPIATAAIAAhSIARAAIAAB8gAgfA5IAAgrIgaAAIAAgOIAVAAIAQAAIAPAAIAPgBIABANIgMABIgNABIAAArgAgjgIQgGgCgEgEQgDgDgCgFQgCgEAAgFIAAgEQAAgEACgFQACgEADgDQAEgEAGgCQAFgCAHgBQAIABAFACQAGACAEAEQACADACAEQACAFAAAEIAAAEQAAAFgCAEQgCAFgCADQgEAEgGACQgFACgIAAQgHAAgFgCgAgdgsIgEACIgCAEIgBAEIAAACIABAEIACAEIAEACQADACADAAQAEAAADgCQADgBABgBIACgEIABgEIAAgCIgBgEIgCgEIgEgCIgHgBIgGABg");
	this.shape_35.setTransform(-63.3,-128.5);

	this.instance = new lib.Path_3();
	this.instance.parent = this;
	this.instance.setTransform(0,-104.5,1,1,0,0,0,84,0.5);
	this.instance.alpha = 0.398;

	this.shape_36 = new cjs.Shape();
	this.shape_36.graphics.f("#FFFFFF").s().p("AgjAjQgOgPAAgUQAAgUAOgPQAPgOAUAAQAUAAAPAOQAPAPAAAUQAAAUgPAPQgPAPgUAAQgUAAgPgPg");
	this.shape_36.setTransform(89,-104.5);

	this.shape_37 = new cjs.Shape();
	this.shape_37.graphics.f("#FFFFFF").s().p("AgiAjQgPgPAAgUQAAgUAPgPQAOgOAUAAQAUAAAPAOQAPAPAAAUQAAAUgPAPQgPAPgUAAQgUAAgOgPg");
	this.shape_37.setTransform(-89,-104.5);

	this.shape_38 = new cjs.Shape();
	this.shape_38.graphics.f("#230AFF").s().p("AsBaLQgyAAgjgjQgjgjAAgyMAAAgwlQAAgyAjgjQAjgjAyAAIYDAAQAxAAAkAjQAjAjAAAyMAAAAwlQAAAygjAjQgkAjgxAAg");
	this.shape_38.setTransform(0,13);

	this.shape_39 = new cjs.Shape();
	this.shape_39.graphics.f("#DDDDDD").s().p("AxBVVMAAAgqpMAiDAAAMAAAAqpgAw3VLMAhvAAAMAAAgqVMghvAAAg");
	this.shape_39.setTransform(0,-44);

	this.shape_40 = new cjs.Shape();
	this.shape_40.graphics.f("#FFFFFF").s().p("AxBVVMAAAgqpMAiDAAAMAAAAqpg");
	this.shape_40.setTransform(0,-44);

	this.timeline.addTween(cjs.Tween.get({}).to({state:[{t:this.shape_40},{t:this.shape_39},{t:this.shape_38},{t:this.shape_37},{t:this.shape_36},{t:this.instance},{t:this.shape_35},{t:this.shape_34},{t:this.shape_33},{t:this.shape_32},{t:this.shape_31},{t:this.shape_30},{t:this.shape_29},{t:this.shape_28},{t:this.shape_27},{t:this.shape_26},{t:this.shape_25},{t:this.shape_24},{t:this.shape_23},{t:this.shape_22},{t:this.shape_21},{t:this.shape_20},{t:this.shape_19},{t:this.shape_18},{t:this.shape_17},{t:this.shape_16},{t:this.shape_15},{t:this.shape_14},{t:this.shape_13},{t:this.shape_12},{t:this.shape_11},{t:this.shape_10},{t:this.shape_9},{t:this.shape_8},{t:this.shape_7},{t:this.shape_6},{t:this.shape_5},{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]}).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-109,-180.5,218,361);


(lib.Tween22 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 1
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#FFFFFF").s().p("AAcApIAAgYIguAAIAAgGIA0AAIAAAegAAcAHIAAgWIgMAAIAAAVIgGAAIAAguIAGAAIAAATIAMAAIAAgUIAGAAIAAAwgAgXAAIgGgEIgDgFIgBgHIAAgEIABgGIADgGIAGgDQAEgCAEAAQAEAAADACIAGADIADAGIABAGIAAAEIgBAHIgDAFIgGAEQgDAAgEABQgEgBgEAAgAgUgfIgEADIgCAEIgBAFIAAADIABAEIACAFIAEACIAFABIAFgBIADgCIADgFIABgEIAAgDIgBgFIgDgEIgDgDIgFgBIgFABg");
	this.shape.setTransform(-41.2,2.1);

	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#FFFFFF").s().p("AgaAnIAAgfIA1AAIAAAfgAgTAhIAnAAIAAgTIgnAAgAglgCIAAgFIA3AAIABgLIABgJIAAgFIgvAAIAAgGIA1AAIAAALQAAAKgCAKIAOAAIAAAFg");
	this.shape_1.setTransform(-49.1,2.3);

	this.shape_2 = new cjs.Shape();
	this.shape_2.graphics.f("#FFFFFF").s().p("AAdApIAAhRIAHAAIAABRgAAOAlIAAgsIgOAAIAAgFIAOAAIAAgbIAGAAIAABMgAgjAQIAEgEIAEgEIADgDIADgDIACgDIABgEIABgEIAAgFIAAgNIgPAAIAAgFIAjAAIAAAFIgNAAIAAANIAAAFIABAEIABAEIADADIAFAGIAHAHIgFAEIgCgDIgCgDIgEgEIgCgDIgDgEIgCgCIgCACIgDAEIgFAHIgHAHg");
	this.shape_2.setTransform(-57.5,2.1);

	this.shape_3 = new cjs.Shape();
	this.shape_3.graphics.f("#FFFFFF").s().p("AgSApIAAgVIArAAIAAgKIgrAAIAAgFIAxAAIAAAVIgrAAIAAAKIAtAAIAAAFgAghgCIASgHQAFgDAEgDQADgCAAgEIABgHIAAgDIgcAAIAAgGIAhAAIAAAJIAAAHQAAADgBACIgEAFIgGAEIgJAFIgNAEgAAZAAIAAgJIgRAAIAAgFIARAAIAAgLIgQAAIAAgFIAQAAIAAgKIAGAAIAAAog");
	this.shape_3.setTransform(-65.5,2.1);

	this.shape_4 = new cjs.Shape();
	this.shape_4.graphics.f("#FFFFFF").s().p("AgeA8IAAghIAQAAIAAATIBBAAIAAAOgAAhAhIAAgJIgYAAIAAgNIAYAAIAAhGIAQAAIAABcgAgVAWIAAgPIgOAAIgPAAIAAgNIAaAAIAVgBIAPAAIAOgBIABAMIgPACIgSABIAAAPgAgYgNQgFgBgFgCQgEgEgCgEQgCgDAAgFIAAgEQAAgEACgEQACgFAEgCQAFgDAFgBQAGgCAGAAQAHAAAFACQAFABAEADQAEACACAFQACAEABAEIAAAEQgBAFgCADQgCAEgEAEQgEACgFABQgFACgHAAQgGAAgGgCgAgXgpQgEADAAADIAAACQAAAEAEACQAFADAGAAQAHAAAEgDQADgCAAgEIAAgCQAAgDgDgDQgEgDgHABQgGgBgFADg");
	this.shape_4.setTransform(63.5,0.8);

	this.shape_5 = new cjs.Shape();
	this.shape_5.graphics.f("#FFFFFF").s().p("AgXBNQgLgEgIgJQgPgSAAgiIAAgXQAAgRAEgNQADgNAIgJQAPgSAbAAQAcAAAPASQAPASAAAiIAAAXQAAAigOASQgOASgdAAQgNAAgLgFgAgKg0QgFABgEAFQgFAEgCAJQgDAIAAANIAAAZQAAANADAJQADAIAEAFQAEAEAGACIAKABQAGAAAFgBQAFgCAEgEQAEgFACgIQADgJAAgNIAAgZQAAgNgDgJQgCgIgEgEQgEgFgGgBIgLgCIgKACg");
	this.shape_5.setTransform(42.7,-0.4);

	this.shape_6 = new cjs.Shape();
	this.shape_6.graphics.f("#FFFFFF").s().p("AgXBNQgLgEgIgJQgPgSAAgiIAAgXQAAgRAEgNQADgNAIgJQAPgSAbAAQAcAAAPASQAPASAAAiIAAAXQAAAigOASQgOASgdAAQgNAAgLgFgAgKg0QgFABgEAFQgFAEgCAJQgDAIAAANIAAAZQAAANADAJQADAIAEAFQAEAEAGACIAKABQAGAAAFgBQAFgCAEgEQAEgFACgIQADgJAAgNIAAgZQAAgNgDgJQgCgIgEgEQgEgFgGgBIgLgCIgKACg");
	this.shape_6.setTransform(29.1,-0.4);

	this.shape_7 = new cjs.Shape();
	this.shape_7.graphics.f("#FFFFFF").s().p("AgbBPQgMgCgIgGIAKgXQAIAEAIACQAHACALAAQAGAAAGgCQAGgCAEgFQAFgFACgJQACgIAAgNQgFAHgKADQgJAEgKAAQgLAAgKgEQgJgDgHgHQgFgHgEgIQgEgJABgMIAAgEQAAgLADgKQAFgKAGgHQAIgHAKgEQALgEAMAAQAPAAALAFQAKAFAIAJQAGAIADANQADAMAAAQIAAAKIgBAXQAAAKgDAJQgEAQgJAKQgIAKgMADQgLAEgOAAQgKAAgLgDgAgLg1QgFACgEADQgEAEgCAFQgCAFAAAGIAAADQAAAOAHAGQADAEAGABQAFABAHAAQAHAAAFgBQAGgCADgEQAEgDACgFQABgFAAgGIAAgDQAAgNgHgHQgDgDgGgCQgEgCgIAAQgFAAgGACg");
	this.shape_7.setTransform(15.4,-0.4);

	this.shape_8 = new cjs.Shape();
	this.shape_8.graphics.f("#FFFFFF").s().p("AgTAaIAJgzIAeAAIgSAzg");
	this.shape_8.setTransform(4.7,8.3);

	this.shape_9 = new cjs.Shape();
	this.shape_9.graphics.f("#FFFFFF").s().p("AgcBPQgKgCgKgGIALgXQAIAEAHACQAIACAKAAQAHAAAGgCQAGgCAFgFQADgFACgJQADgIABgNQgGAHgKADQgKAEgJAAQgMAAgIgEQgKgDgGgHQgHgHgDgIQgEgJAAgMIAAgEQAAgLAFgKQADgKAHgHQAIgHAKgEQAKgEANAAQAPAAALAFQALAFAGAJQAHAIAEANQADAMAAAQIAAAKIgBAXQgBAKgDAJQgEAQgJAKQgIAKgLADQgMAEgNAAQgLAAgMgDgAgLg1QgFACgFADQgDAEgCAFQgCAFAAAGIAAADQAAAOAHAGQADAEAGABQAFABAHAAQAHAAAFgBQAGgCADgEQAEgDACgFQACgFAAgGIAAgDQAAgNgIgHQgEgDgFgCQgEgCgHAAQgHAAgFACg");
	this.shape_9.setTransform(-5.7,-0.4);

	this.shape_10 = new cjs.Shape();
	this.shape_10.graphics.f().s("#FFFFFF").p("Aq2AAIVtAA");
	this.shape_10.setTransform(-0.5,-25);

	this.shape_11 = new cjs.Shape();
	this.shape_11.graphics.f("#FFFFFF").s().p("AgXApIAAgbIAGAAIAAAVIAsAAIAAAGgAATASIAAg6IAGAAIAAAYIAMAAIAAAGIgMAAIAAAcgAgkAFIAMgFIAIgFIAGgEIADgEIABgEIABgFIAAgHIgdAAIAAgFIAiAAIAAAMIAAAHIgBAFIgEAFIgGAFIgKAEIgNAGg");
	this.shape_11.setTransform(-40.8,-38);

	this.shape_12 = new cjs.Shape();
	this.shape_12.graphics.f("#FFFFFF").s().p("AAZApIAAhRIAHAAIAABRgAgfARIARgKQAGgEADgDQADgEABgEIABgIIAAgLIgeAAIAAgGIAjAAIAAARIAAAHIgCAGIgDAFIgGAGIgJAHIgNAHg");
	this.shape_12.setTransform(-49.3,-37.9);

	this.shape_13 = new cjs.Shape();
	this.shape_13.graphics.f("#FFFFFF").s().p("AglAkIAAgGIAWAAIAAgQQgFgCgBgDQgDgCAAgEIAAgDQAAgDACgDIAFgEIAIgDIAJgBIAJABIAIADIAGAEQACADAAADIAAADQAAAEgCACQgDADgEACIAAAQIAWAAIAAAGgAgJAeIATAAIAAgOIgKABIgJgBgAgGgIIgGACIgFADIgBADIAAACIABAEIAFADIAGACIAGABIAHgBIAHgCIADgDIACgEIAAgCIgCgDIgDgDIgHgCIgHgBIgGABgAgegTIAAgFIAcAAIAAgLIAFAAIAAALIAcAAIAAAFg");
	this.shape_13.setTransform(-57.2,-38.6);

	this.shape_14 = new cjs.Shape();
	this.shape_14.graphics.f("#FFFFFF").s().p("AALAoIAAgcIgVAAIAAAcIgGAAIAAgcIgVAAIAAgFIBLAAIAAAFIgUAAIAAAcgAgJgCIgIgEQgFgCgCgDQgCgDAAgEIAAgEQAAgEACgDQACgEAFgCQAEgCAEgBIAJgBIAKABQAFABAEACQADACADAEQACADAAAEIAAAEQAAAEgCADQgDADgDACIgJAEIgKABIgJgBgAgHghIgHADIgEAEQgCABAAADIAAAEQAAACACACIAEAEIAHACIAHABIAIgBIAGgCIAFgEIABgEIAAgEQABgDgCgBIgFgEIgGgDIgIAAIgHAAg");
	this.shape_14.setTransform(-65.4,-37.8);

	this.shape_15 = new cjs.Shape();
	this.shape_15.graphics.f("#FFFFFF").s().p("AgVAqIAAgUIArAAIAAgEIgrAAIAAgIIA2AAIAAAUIgrAAIAAAEIAuAAIAAAIgAAWAHIAAgBIgRAAIAAgHIARAAIAAgoIALAAIAAAwgAgPAHIAAgJIgKAAIgKAAIAAgJIASAAIAOAAIALgBIAJAAIABAIIgKABIgMAAIAAAKgAgRgOIgGgDQgDgBgCgDQgBgCAAgDIAAgCQAAgDABgCQACgDADgCIAGgCIAJAAIAIAAIAFACQADACACADQACACAAADIAAACQAAADgCACQgCADgDABIgFADIgIABIgJgBgAgQgfQgBAAAAABQgBAAAAABQAAAAgBABQAAAAAAAAIAAABQAAABAAAAQABABAAAAQAAABABAAQAAABABAAIAIABIAHgBQABAAAAgBQAAAAAAgBQAAAAABgBQAAAAAAgBIAAgBQAAAAAAAAQgBgBAAAAQAAgBAAAAQAAgBgBAAIgHgBIgIABg");
	this.shape_15.setTransform(65.4,-37.9);

	this.shape_16 = new cjs.Shape();
	this.shape_16.graphics.f("#FFFFFF").s().p("AAaAqIAAgtIgIAAIAAApIgLAAIAAhNIALAAIAAAbIAIAAIAAgdIAKAAIAABTgAgkAQIANgJIAHgHQADgCAAgFIABgIIAAgIIgWAAIAAgKIAhAAIAAATIgBALQgBAEgDAEIgJAJIgOALg");
	this.shape_16.setTransform(57.2,-37.9);

	this.shape_17 = new cjs.Shape();
	this.shape_17.graphics.f("#FFFFFF").s().p("AAHAyIAAhPIgWANIgJgPIAhgTIAQAAIAABkg");
	this.shape_17.setTransform(45,-37.7);

	this.shape_18 = new cjs.Shape();
	this.shape_18.graphics.f().s("#FFFFFF").p("Aq2AAIVtAA");
	this.shape_18.setTransform(-0.5,-65);

	this.shape_19 = new cjs.Shape();
	this.shape_19.graphics.f("#FFFFFF").s().p("AglAgIAAgGIAjAAIAAgVIgYAAIAAgkIA1AAIAAAGIgvAAIAAAZIAwAAIAAAFIgZAAIAAAVIAjAAIAAAGg");
	this.shape_19.setTransform(-24.6,-78.2);

	this.shape_20 = new cjs.Shape();
	this.shape_20.graphics.f("#FFFFFF").s().p("AgWApIAAgXIAGAAIAAARIAsAAIAAAGgAATAUIAAg7IAHAAIAAAZIAMAAIAAAFIgMAAIAAAdgAgVALIgGgEIgEgEIgBgFIAAgDIABgGIAEgEIAGgDQADgCAEAAQAFAAADACIAGADIADAEIABAGIAAADIgBAFIgDAEIgGAEIgIABIgHgBgAgTgNIgEACIgDADIAAADIAAADIAAACIADADIAEACIAFABIAGgBIAEgCIACgDIABgCIAAgDIgBgDIgCgDIgEgCIgGgBIgFABgAglgYIAAgGIAUAAIAAgKIAHAAIAAAKIATAAIAAAGg");
	this.shape_20.setTransform(-32.7,-78);

	this.shape_21 = new cjs.Shape();
	this.shape_21.graphics.f("#FFFFFF").s().p("AgJAoIgIgDQgDgBgCgDQgCgDAAgEIAAgCQAAgFACgCIAFgEQADgDAFAAIAJgBIAKABIAIADIAFAEQACADAAAEIAAACQAAAEgCADQgCADgDABIgIADIgKABIgJgBgAgGAPIgHABIgEADIgCAFIAAACQAAABABAAQAAABAAAAQAAABAAAAQABABAAAAIAEADIAHACIAGAAIAHAAIAGgCIAEgDQABAAAAgBQABAAAAgBQAAAAAAgBQABAAAAgBIAAgCQAAgBgBAAQAAgBAAgBQAAAAgBgBQAAAAgBgBIgEgDIgGgBIgHgBIgGABgAglABIAAgEIBLAAIAAAEgAgZgNIAAgbIAGAAIAAAVIAuAAIAAAGg");
	this.shape_21.setTransform(-40.9,-77.7);

	this.shape_22 = new cjs.Shape();
	this.shape_22.graphics.f("#FFFFFF").s().p("AARApIAAhRIAHAAIAAAfIANAAIAAAGIgNAAIAAAsgAglARIAMgHIAJgGIAFgEIADgFIACgFIAAgHIAAgKIgeAAIAAgFIAkAAIAAAQIgBAKIgBAFIgEADIgKAJIgRALg");
	this.shape_22.setTransform(-48.9,-77.9);

	this.shape_23 = new cjs.Shape();
	this.shape_23.graphics.f("#FFFFFF").s().p("AAbApIAAgtIgMAAIAAApIgGAAIAAhMIAGAAIAAAdIAMAAIAAgeIAGAAIAABRgAggAUIAAgeIAXAAIAAgSIgXAAIAAgFIAeAAIAAAdIgYAAIAAASIAGAAIAGAAIAFAAIAGgBIAGgBIAAAGIgMABIgMABg");
	this.shape_23.setTransform(-57.3,-77.9);

	this.shape_24 = new cjs.Shape();
	this.shape_24.graphics.f("#FFFFFF").s().p("AAaApIAAguIgXAAIgDAGIgFAGIgJAHIgOAIIgEgFIAMgHIAJgGIAGgEIACgFIACgFIAAgGIAAgLIgeAAIAAgFIAjAAIAAAQIAAAFIAWAAIAAgdIAGAAIAABRg");
	this.shape_24.setTransform(-65.6,-77.9);

	this.shape_25 = new cjs.Shape();
	this.shape_25.graphics.f("#FFFFFF").s().p("AgVAqIAAgXIAMAAIAAANIAsAAIAAAKgAAWAXIAAgGIgQAAIAAgJIAQAAIAAgxIAMAAIAABAgAgPAQIAAgLIgJAAIgKAAIAAgIIASgBIAOAAIAKAAIAJgBIABAIIgKABIgMABIAAALgAgQgIIgHgDIgEgFQgCgDAAgDIAAgCQAAgEACgCQABgDADgCIAHgDIAIgBQAEAAAEABQADABADACQADACABADQACACAAAEIAAACQAAADgCADIgEAFQgDACgDABIgIABIgIgBgAgPgcQgDACAAACIAAACQAAAAAAABQAAABAAAAQABAAAAABQABAAABABQACACAFAAQAFAAACgCQACgCAAgCIAAgCQAAgCgCgCQgCgCgFAAQgFAAgCACg");
	this.shape_25.setTransform(65.4,-78);

	this.shape_26 = new cjs.Shape();
	this.shape_26.graphics.f("#FFFFFF").s().p("AgZApIAAgcIAMAAIAAATIApAAIAAAJgAAOATIAAg8IAMAAIAAAXIALAAIAAAKIgLAAIAAAbgAgkAEIAAgnIAmAAIAAAngAgZgEIAQAAIAAgVIgQAAg");
	this.shape_26.setTransform(57.6,-78);

	this.shape_27 = new cjs.Shape();
	this.shape_27.graphics.f("#FFFFFF").s().p("AgPAyQgGgEgFgFQgKgMAAgVIAAgPQAAgKACgJQACgIAGgFQAKgMAQAAQASAAAKAMQAJALAAAVIAAAPQAAAVgJAMQgJALgSAAQgIAAgIgCgAgGghQgEABgCADIgEAIQgCAGAAAIIAAAPQAAAJACAFQACAGACADQADADADAAIAHABIAHgBQADAAACgDQADgEACgEQABgGAAgJIAAgPQAAgIgBgGIgFgIQgDgDgDgBIgHgBIgGABg");
	this.shape_27.setTransform(45.3,-77.7);

	this.shape_28 = new cjs.Shape();
	this.shape_28.graphics.f("#FFFFFF").s().p("AgPAyQgHgEgEgFQgKgMAAgVIAAgPQAAgKADgJQABgIAGgFQAJgMARAAQASAAAKAMQAJALAAAVIAAAPQAAAVgJAMQgJALgSAAQgIAAgIgCgAgGghQgEABgCADIgEAIQgCAGAAAIIAAAPQAAAJACAFQACAGACADQADADADAAIAHABIAHgBQADAAACgDQADgEACgEQABgGAAgJIAAgPQAAgIgBgGIgFgIQgDgDgDgBIgHgBIgGABg");
	this.shape_28.setTransform(36.7,-77.7);

	this.shape_29 = new cjs.Shape();
	this.shape_29.graphics.f("#FFFFFF").s().p("AgjAzIAAgNIAkgmIAHgHIAEgGIACgFIAAgEIAAgCQAAgEgDgDIgFgCIgGgBIgFAAIgFACIgGAFIgFAHIgNgJIAEgHIAHgHQAEgDAGgCQAGgCAHAAQAIAAAGACQAGACAEAEQAEADACAFQACAFAAAFIAAABIgBAIIgDAIQgDAHgIAHIgcAeIAvAAIAAAOg");
	this.shape_29.setTransform(27.9,-77.8);

	this.shape_30 = new cjs.Shape();
	this.shape_30.graphics.f("#FFFFFF").s().p("AgcBBIAAg5IASAAIAAAOIAuAAIAAgOIASAAIAAA5gAgKAyIAuAAIAAgNIguAAgAg1gHIAMgJIAKgIQAFgEACgFQACgFAAgGIAAgQIASAAIAAAPIABAGIABAFIACAFIAFAEIAJAHIALAHIgKALIgRgLQgIgGgCgDIgDAEIgHAGIgJAHIgLAIgAAkACIAAhCIASAAIAABCg");
	this.shape_30.setTransform(3.3,-128.5);

	this.shape_31 = new cjs.Shape();
	this.shape_31.graphics.f("#FFFFFF").s().p("AAiBBIAAhGIgXAAIAAAkIg+AAIAAhUIASAAIAAAdIAcAAIAAgdIAQAAIAAAhIAXAAIAAgsIASAAIAACBgAghARIAcAAIAAgaIgcAAg");
	this.shape_31.setTransform(-8.9,-128.5);

	this.shape_32 = new cjs.Shape();
	this.shape_32.graphics.f("#FFFFFF").s().p("AgeBBIAAgzIBUAAIAAAzgAgMAyIAwAAIAAgVIgwAAgAAlAJIAAhJIARAAIAABJgAAMAJIAAgeIgMAAIAAAWIg1AAIAAg6IA1AAIAAAWIAMAAIAAgbIAQAAIAABHgAgkgNIATAAIAAgdIgTAAg");
	this.shape_32.setTransform(-21.8,-128.5);

	this.shape_33 = new cjs.Shape();
	this.shape_33.graphics.f("#FFFFFF").s().p("Ag7A0IAAgQIB3AAIAAAQgAAkAPIgIgFIgIgEIgHgEIgJgEQgDgDAAgCIgBAAQAAACgDADIgJAEIgHAFIgIAEIgIAFIgIADIgIgOIAQgHIAOgIQAHgEADgFQADgFAAgGIAAgFIgkAAIAAgQIBZAAIAAAQIgkAAIAAAFQAAAGADAFQADAFAHAEIAOAIIAQAHIgIAOg");
	this.shape_33.setTransform(-37.7,-128.7);

	this.shape_34 = new cjs.Shape();
	this.shape_34.graphics.f("#FFFFFF").s().p("AAiBBIAAiBIARAAIAACBgAgZAiIgHgDQgGgCgFgFQgEgFgBgHQgCgHAAgHIAAgPQAAgJACgHQABgHAEgEQAFgGAGgCQAHgDAIgBQAJABAGADQAGACAEAGQAEAEACAHQACAHAAAJIAAAPQAAAHgCAHQgCAHgEAFQgEAFgGACIgHADIgIABgAgdghIgDAHIgCALIAAALIACAJIADAIQABADADACQAEABAEAAQAEAAAEgBQADgCACgDQAEgGAAgLIAAgLQAAgNgEgFQgEgHgJAAQgJAAgDAHg");
	this.shape_34.setTransform(-50.6,-128.5);

	this.shape_35 = new cjs.Shape();
	this.shape_35.graphics.f("#FFFFFF").s().p("AAoBBIAAiBIARAAIAACBgAAPA+IAAgbIgTAAIAAgPIATAAIAAhSIARAAIAAB8gAgfA5IAAgrIgaAAIAAgOIAVAAIAQAAIAPAAIAPgBIABANIgMABIgNABIAAArgAgjgIQgGgCgEgEQgDgDgCgFQgCgEAAgFIAAgEQAAgEACgFQACgEADgDQAEgEAGgCQAFgCAHgBQAIABAFACQAGACAEAEQACADACAEQACAFAAAEIAAAEQAAAFgCAEQgCAFgCADQgEAEgGACQgFACgIAAQgHAAgFgCgAgdgsIgEACIgCAEIgBAEIAAACIABAEIACAEIAEACQADACADAAQAEAAADgCQADgBABgBIACgEIABgEIAAgCIgBgEIgCgEIgEgCIgHgBIgGABg");
	this.shape_35.setTransform(-63.3,-128.5);

	this.instance = new lib.Path_3();
	this.instance.parent = this;
	this.instance.setTransform(0,-104.5,1,1,0,0,0,84,0.5);
	this.instance.alpha = 0.398;

	this.shape_36 = new cjs.Shape();
	this.shape_36.graphics.f("#FFFFFF").s().p("AgjAjQgOgPAAgUQAAgUAOgPQAPgOAUAAQAUAAAPAOQAPAPAAAUQAAAUgPAPQgPAPgUAAQgUAAgPgPg");
	this.shape_36.setTransform(89,-104.5);

	this.shape_37 = new cjs.Shape();
	this.shape_37.graphics.f("#FFFFFF").s().p("AgiAjQgPgPAAgUQAAgUAPgPQAOgOAUAAQAUAAAPAOQAPAPAAAUQAAAUgPAPQgPAPgUAAQgUAAgOgPg");
	this.shape_37.setTransform(-89,-104.5);

	this.shape_38 = new cjs.Shape();
	this.shape_38.graphics.f("#230AFF").s().p("AsBaLQgyAAgjgjQgjgjAAgyMAAAgwlQAAgyAjgjQAjgjAyAAIYDAAQAxAAAkAjQAjAjAAAyMAAAAwlQAAAygjAjQgkAjgxAAg");
	this.shape_38.setTransform(0,13);

	this.shape_39 = new cjs.Shape();
	this.shape_39.graphics.f("#DDDDDD").s().p("AxBVVMAAAgqpMAiDAAAMAAAAqpgAw3VLMAhvAAAMAAAgqVMghvAAAg");
	this.shape_39.setTransform(0,-44);

	this.shape_40 = new cjs.Shape();
	this.shape_40.graphics.f("#FFFFFF").s().p("AxBVVMAAAgqpMAiDAAAMAAAAqpg");
	this.shape_40.setTransform(0,-44);

	this.timeline.addTween(cjs.Tween.get({}).to({state:[{t:this.shape_40},{t:this.shape_39},{t:this.shape_38},{t:this.shape_37},{t:this.shape_36},{t:this.instance},{t:this.shape_35},{t:this.shape_34},{t:this.shape_33},{t:this.shape_32},{t:this.shape_31},{t:this.shape_30},{t:this.shape_29},{t:this.shape_28},{t:this.shape_27},{t:this.shape_26},{t:this.shape_25},{t:this.shape_24},{t:this.shape_23},{t:this.shape_22},{t:this.shape_21},{t:this.shape_20},{t:this.shape_19},{t:this.shape_18},{t:this.shape_17},{t:this.shape_16},{t:this.shape_15},{t:this.shape_14},{t:this.shape_13},{t:this.shape_12},{t:this.shape_11},{t:this.shape_10},{t:this.shape_9},{t:this.shape_8},{t:this.shape_7},{t:this.shape_6},{t:this.shape_5},{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]}).wait(1));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-109,-180.5,218,361);


// stage content:
(lib.Untitled4 = function(mode,startPosition,loop) {
	this.initialize(mode,startPosition,loop,{});

	// Layer 2
	this.instance = new lib.Tween20("synched",0);
	this.instance.parent = this;
	this.instance.setTransform(862.8,897.1);
	this.instance._off = true;

	this.instance_1 = new lib.Tween21("synched",0);
	this.instance_1.parent = this;
	this.instance_1.setTransform(862.5,553);
	this.instance_1._off = true;

	this.timeline.addTween(cjs.Tween.get(this.instance).wait(10).to({_off:false},0).to({_off:true,x:862.5,y:553},5).wait(903));
	this.timeline.addTween(cjs.Tween.get(this.instance_1).wait(10).to({_off:false},5).wait(45).to({startPosition:0},0).to({alpha:0.199},3).to({alpha:1},3).wait(52).to({startPosition:0},0).wait(781).to({startPosition:0},0).to({x:862.8,y:897.1},5).wait(14));

	// Layer 2
	this.instance_2 = new lib.Tween22("synched",0);
	this.instance_2.parent = this;
	this.instance_2.setTransform(863.3,851.6);
	this.instance_2._off = true;

	this.instance_3 = new lib.Tween23("synched",0);
	this.instance_3.parent = this;
	this.instance_3.setTransform(863,507.5);
	this.instance_3._off = true;

	this.timeline.addTween(cjs.Tween.get(this.instance_2).wait(10).to({_off:false},0).to({_off:true,x:863,y:507.5},5).wait(903));
	this.timeline.addTween(cjs.Tween.get(this.instance_3).wait(10).to({_off:false},5).wait(884).to({startPosition:0},0).to({x:863.3,y:851.6},5).wait(14));

	// Layer 1
	this.instance_4 = new lib.Tween24("synched",0);
	this.instance_4.parent = this;
	this.instance_4.setTransform(863.3,864.5);
	this.instance_4._off = true;

	this.instance_5 = new lib.Tween25("synched",0);
	this.instance_5.parent = this;
	this.instance_5.setTransform(863,520.5);
	this.instance_5._off = true;

	this.timeline.addTween(cjs.Tween.get(this.instance_4).wait(10).to({_off:false},0).to({_off:true,x:863,y:520.5},5).wait(903));
	this.timeline.addTween(cjs.Tween.get(this.instance_5).wait(10).to({_off:false},5).wait(884).to({startPosition:0},0).to({x:863.3,y:864.5},5).wait(14));

	// COPY1
	this.instance_6 = new lib.Tween19("synched",0);
	this.instance_6.parent = this;
	this.instance_6.setTransform(438,472.7);
	this.instance_6.alpha = 0;
	this.instance_6._off = true;

	this.timeline.addTween(cjs.Tween.get(this.instance_6).wait(14).to({_off:false},0).to({alpha:1},5).wait(880).to({startPosition:0},0).to({y:657.5},5).wait(14));

	// RATES
	this.instance_7 = new lib.Tween14("synched",0);
	this.instance_7.parent = this;
	this.instance_7.setTransform(-484.8,300.2);
	this.instance_7._off = true;

	this.instance_8 = new lib.Tween15("synched",0);
	this.instance_8.parent = this;
	this.instance_8.setTransform(185.1,300.6);
	this.instance_8._off = true;

	this.timeline.addTween(cjs.Tween.get(this.instance_7).wait(5).to({_off:false},0).to({_off:true,x:185.1,y:300.6},9).wait(904));
	this.timeline.addTween(cjs.Tween.get(this.instance_8).wait(5).to({_off:false},9).wait(106).to({startPosition:0},0).wait(3).to({startPosition:0},0).to({alpha:0.801},3).to({alpha:0.91},3).wait(111).to({alpha:1},0).wait(3).to({startPosition:0},0).to({alpha:0.801},3).to({alpha:0.91},3).wait(1).to({startPosition:0},0).wait(110).to({alpha:1},0).wait(3).to({startPosition:0},0).to({alpha:0.801},3).to({alpha:0.91},3).wait(1).to({startPosition:0},0).wait(110).to({alpha:1},0).wait(3).to({startPosition:0},0).to({alpha:0.801},3).to({alpha:0.91},3).wait(1).to({startPosition:0},0).wait(110).to({alpha:1},0).wait(3).to({startPosition:0},0).to({alpha:0.801},3).to({alpha:0.91},3).wait(1).to({startPosition:0},0).wait(110).to({alpha:1},0).wait(3).to({startPosition:0},0).to({alpha:0.801},3).to({alpha:0.91},3).wait(1).to({startPosition:0},0).wait(110).to({alpha:1},0).wait(3).to({startPosition:0},0).to({alpha:0.801},3).to({alpha:0.91},3).wait(1).to({startPosition:0},0).wait(49).to({alpha:1},0).to({x:-490.9},5).wait(14));

	// EXCHANGE
	this.instance_9 = new lib.Tween13("synched",0);
	this.instance_9.parent = this;
	this.instance_9.setTransform(-484.8,167.9);

	this.timeline.addTween(cjs.Tween.get(this.instance_9).to({x:317.3},10).wait(110).to({startPosition:0},0).to({alpha:0.699},3).to({alpha:1},3).wait(114).to({startPosition:0},0).to({alpha:0.699},3).to({alpha:1},3).wait(4).to({startPosition:0},0).wait(110).to({startPosition:0},0).to({alpha:0.699},3).to({alpha:1},3).wait(4).to({startPosition:0},0).wait(110).to({startPosition:0},0).to({alpha:0.699},3).to({alpha:1},3).wait(4).to({startPosition:0},0).wait(110).to({startPosition:0},0).to({alpha:0.699},3).to({alpha:1},3).wait(4).to({startPosition:0},0).wait(110).to({startPosition:0},0).to({alpha:0.699},3).to({alpha:1},3).wait(4).to({startPosition:0},0).wait(110).to({startPosition:0},0).to({alpha:0.699},3).to({alpha:1},3).wait(4).to({startPosition:0},0).wait(49).to({startPosition:0},0).to({x:-358.7},5).wait(14));

	// CI
	this.shape = new cjs.Shape();
	this.shape.graphics.f("#00003A").s().p("AgUDvIAAg5IhoAAIAAhHIBoAAIAAhMIgNAAQgtAAgfggQghgfAAgtQAAgtAfgfQAfggAtgBIAPAAIAAg4IAxAAIAAA4IBgAAIAABHIhgAAIAABMIAFAAQAtAAAfAgQAgAfABAtQgBAtgfAgQgeAfgtABIgHAAIAAA5gAAdBvIAFAAQAQAAALgLQAKgLABgQQgBgQgKgLQgMgLgPAAIgFAAgAg8hkQgLALAAAQQAAAQALALQAMALAPAAIANAAIAAhMIgNAAQgPAAgMALg");
	this.shape.setTransform(957.9,119.6);

	this.shape_1 = new cjs.Shape();
	this.shape_1.graphics.f("#00003A").s().p("AA4CjIgiiaIgpAAIghCaIg8AAIgtiaIg8AAIAAgsIAuAAIgmh/IA9AAIAiB/IA3AAIAZh0IBFAAIAZB0IA4AAIAih/IA8AAIglB/IAtAAIAAAsIg6AAIgsCagABVBMIAShDIghAAgAhTBMIAOhDIghAAgAAMgjIgMgyIgJAyIAVAAg");
	this.shape_1.setTransform(768,119.5);

	this.shape_2 = new cjs.Shape();
	this.shape_2.graphics.f("#00003A").s().p("AAzC3Qg1AAgrgjQgpgigMg1IgwAAIAAgtIAtAAIAAgfIgtAAIAAgtIAwAAQAMg1ApgiQArgjA1AAIBgAAIAABHIhgAAQgZAAgVAOQgTAOgKAXIBbAAIAAAtIhhAAIAAAfIBhAAIAAAtIhbAAQAKAXATAOQAVAOAZAAIBgAAIAABHg");
	this.shape_2.setTransform(829.1,119.5);

	this.shape_3 = new cjs.Shape();
	this.shape_3.graphics.f("#00003A").s().p("AghC3IAAhOIhKAAIAAgrIBKAAIAAgaIhKAAIAAgrIBJAAIh3ivIBRAAIABACIBHBnIBJhpIBRAAIh3CvIBJAAIAAArIhKAAIAAAaIBKAAIAAArIhKAAIAABOg");
	this.shape_3.setTransform(895,119.5);

	this.shape_4 = new cjs.Shape();
	this.shape_4.graphics.f("#00003A").s().p("AkZEaQh2h0AAimQAAikB2h2QB0h1ClAAQClAAB2B1QB1B2AACkQAACmh1B0Qh2B2ilAAQikAAh1h2gAj1j2QhnBnABCPQgBCQBnBmQBlBnCQAAQCQAABnhnQBlhmABiQQgBiPhlhnQhnhliQgBQiQABhlBlg");
	this.shape_4.setTransform(768.4,119.6);

	this.shape_5 = new cjs.Shape();
	this.shape_5.graphics.f("#FFFFFF").s().p("AkIEJQhthuAAibQAAiaBthuQBuhtCaAAQCbAABuBtQBtBuAACaQAACbhtBuQhuBtibAAQiaAAhuhtg");
	this.shape_5.setTransform(768.4,119.5);

	this.shape_6 = new cjs.Shape();
	this.shape_6.graphics.f("#00003A").s().p("AkaEaQh1h0AAimQAAikB1h2QB2h1CkAAQClAAB2B1QB1B2AACkQAACmh1B0Qh2B2ilAAQikAAh2h2gAj2j2QhlBnAACPQAACQBlBmQBmBnCQAAQCQAABmhnQBmhmAAiQQABiPhnhnQhmhliQgBQiQABhmBlg");
	this.shape_6.setTransform(831.7,119.6);

	this.shape_7 = new cjs.Shape();
	this.shape_7.graphics.f("#FFFFFF").s().p("AkIEJQhthuAAibQAAiaBthuQBuhtCaAAQCbAABuBtQBtBuAACaQAACbhtBuQhuBtibAAQiaAAhuhtg");
	this.shape_7.setTransform(831.7,119.5);

	this.shape_8 = new cjs.Shape();
	this.shape_8.graphics.f("#00003A").s().p("AkZEaQh2h0AAimQAAikB2h2QB0h1ClAAQCmAAB0B1QB2B2AACkQAACmh2B0Qh0B2imAAQilAAh0h2gAj1j2QhnBnAACPQAACQBnBmQBmBnCPAAQCQAABmhnQBnhmAAiQQAAiPhnhnQhmhliQgBQiPABhmBlg");
	this.shape_8.setTransform(895,119.6);

	this.shape_9 = new cjs.Shape();
	this.shape_9.graphics.f("#FFFFFF").s().p("AkIEJQhthuAAibQAAiaBthuQBuhtCaAAQCbAABuBtQBtBuAACaQAACbhtBuQhuBtibAAQiaAAhuhtg");
	this.shape_9.setTransform(895,119.5);

	this.shape_10 = new cjs.Shape();
	this.shape_10.graphics.f("#00003A").s().p("AkZEaQh2h0AAimQAAikB2h2QB0h1ClAAQClAAB2B1QB1B2AACkQAACmh1B0Qh2B2ilAAQilAAh0h2gAj1j2QhmBnAACPQAACQBmBmQBmBnCPAAQCQAABnhnQBlhmAAiQQAAiPhlhnQhnhliQgBQiPABhmBlg");
	this.shape_10.setTransform(958.3,119.6);

	this.shape_11 = new cjs.Shape();
	this.shape_11.graphics.f("#FFFFFF").s().p("AkIEJQhthuAAibQAAiaBthuQBuhtCaAAQCbAABuBtQBtBuAACaQAACbhtBuQhuBtibAAQiaAAhuhtg");
	this.shape_11.setTransform(958.3,119.5);

	this.instance_10 = new lib.Tween26("synched",0);
	this.instance_10.parent = this;
	this.instance_10.setTransform(863.4,119.6);

	this.timeline.addTween(cjs.Tween.get({}).to({state:[{t:this.shape_11},{t:this.shape_10},{t:this.shape_9},{t:this.shape_8},{t:this.shape_7},{t:this.shape_6},{t:this.shape_5},{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]}).to({state:[{t:this.shape_11},{t:this.shape_10},{t:this.shape_9},{t:this.shape_8},{t:this.shape_7},{t:this.shape_6},{t:this.shape_5},{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]},5).to({state:[{t:this.shape_11},{t:this.shape_10},{t:this.shape_9},{t:this.shape_8},{t:this.shape_7},{t:this.shape_6},{t:this.shape_5},{t:this.shape_4},{t:this.shape_3},{t:this.shape_2},{t:this.shape_1},{t:this.shape}]},894).to({state:[{t:this.instance_10}]},5).wait(14));

	// Layer 1
	this.instance_11 = new lib.Path_1();
	this.instance_11.parent = this;
	this.instance_11.setTransform(622.5,300,1,1,0,0,0,457.5,300);
	this.instance_11.alpha = 0;
	this.instance_11._off = true;

	this.timeline.addTween(cjs.Tween.get(this.instance_11).wait(8).to({_off:false},0).to({alpha:0.801},5).wait(886).to({alpha:0},5).wait(14));

	// BG
	this.shape_12 = new cjs.Shape();
	this.shape_12.graphics.f("#230AFF").s().p("EhUXAu4MAAAhdvMCovAAAMAAABdvg");
	this.shape_12.setTransform(540,300);

	this.timeline.addTween(cjs.Tween.get(this.shape_12).wait(918));

}).prototype = p = new cjs.MovieClip();
p.nominalBounds = new cjs.Rectangle(-283.1,300,1903.1,600);
// library properties:
lib.properties = {
	id: '11CCBE34E65B4DDBB00DC88E394B7690',
	width: 1080,
	height: 600,
	fps: 30,
	color: "#FFFFFF",
	opacity: 1.00,
	webfonts: {},
	manifest: [
		{src:"/weys/resources/img/Layer1.png", id:"Layer1"}
	],
	preloads: []
};



// bootstrap callback support:

(lib.Stage = function(canvas) {
	createjs.Stage.call(this, canvas);
}).prototype = p = new createjs.Stage();

p.setAutoPlay = function(autoPlay) {
	this.tickEnabled = autoPlay;
}
p.play = function() { this.tickEnabled = true; this.getChildAt(0).gotoAndPlay(this.getTimelinePosition()) }
p.stop = function(ms) { if(ms) this.seek(ms); this.tickEnabled = false; }
p.seek = function(ms) { this.tickEnabled = true; this.getChildAt(0).gotoAndStop(lib.properties.fps * ms / 1000); }
p.getDuration = function() { return this.getChildAt(0).totalFrames / lib.properties.fps * 1000; }

p.getTimelinePosition = function() { return this.getChildAt(0).currentFrame / lib.properties.fps * 1000; }

an.bootcompsLoaded = an.bootcompsLoaded || [];
if(!an.bootstrapListeners) {
	an.bootstrapListeners=[];
}

an.bootstrapCallback=function(fnCallback) {
	an.bootstrapListeners.push(fnCallback);
	if(an.bootcompsLoaded.length > 0) {
		for(var i=0; i<an.bootcompsLoaded.length; ++i) {
			fnCallback(an.bootcompsLoaded[i]);
		}
	}
};

an.compositions = an.compositions || {};
an.compositions['11CCBE34E65B4DDBB00DC88E394B7690'] = {
	getStage: function() { return exportRoot.getStage(); },
	getLibrary: function() { return lib; },
	getSpriteSheet: function() { return ss; },
	getImages: function() { return img; }
};

an.compositionLoaded = function(id) {
	an.bootcompsLoaded.push(id);
	for(var j=0; j<an.bootstrapListeners.length; j++) {
		an.bootstrapListeners[j](id);
	}
}

an.getComposition = function(id) {
	return an.compositions[id];
}



})(createjs = createjs||{}, AdobeAn = AdobeAn||{});
var createjs, AdobeAn;