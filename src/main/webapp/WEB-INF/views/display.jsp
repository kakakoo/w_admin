<!DOCTYPE html>
<html lang="UTF-8">
<head>
<link rel="icon" href="./img/favicon.png">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes"> 
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
<script src="https://code.createjs.com/createjs-2015.11.26.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.shuffleText.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/mine.css?v=8" rel="stylesheet">
<script type="text/javascript">
var shuffleSpeed = 1000000;
var shuffleFrame = 20000;

$(function(){
	init();
    display_data();
	
	var interval2 = setInterval(function(){
        display_data();
    }, 300000);
	var interval = setInterval(function(){
		chaffle_usd();
    }, 61111);
	var interval3 = setInterval(function(){
		blink();
    }, 1000);
	var interval1 = setInterval(function(){
		dt();
    }, 3500);
});

function dt(){
	var d = new Date();
	
	var month = (d.getMonth() + 1);
	if(month < 10)
		month = "0" + month;
	var dat = d.getDate();
	if(dat < 10)
		dat = "0" + dat;
	var hour = d.getHours();
	if(hour < 10)
		hour = "0" + hour;
	var min = d.getMinutes();
	if(min < 10)
		min = "0" + min;

    var dttm = d.getFullYear() + "-" + month + "-" + dat + " " + hour + '<span class="blink">:</span>' + min;
    $('#title_dttm').html(dttm);

}

function blink(){
	$('.blink').fadeOut(500);
    $('.blink').fadeIn(500);
}

function chaffle_usd(){
	var text = $('#USD_WEYS').html();
	$("#USD_WEYS").shuffleText(text, {
		frames   : shuffleFrame,    // Duration In ms (Milliseconds) Of Shuffle For Each Letter
		maxSpeed : shuffleSpeed,  // Max Duration In ms (Milliseconds) Of Global Shuffle
		amount   : 5,     // Amount Of Shuffle For Each Letter
		complete : chaffle_usd1   // Do Something When Shuffle Is Completed
		});
	
}
function chaffle_usd1(){
	var text = $('#USD_NON').html();
	$("#USD_NON").shuffleText(text, {
		frames   : shuffleFrame,    // Duration In ms (Milliseconds) Of Shuffle For Each Letter
		maxSpeed : shuffleSpeed,  // Max Duration In ms (Milliseconds) Of Global Shuffle
		amount   : 5,     // Amount Of Shuffle For Each Letter
		complete : chaffle_jpy   // Do Something When Shuffle Is Completed
		});
}

function chaffle_jpy(){
	var text = $('#JPY_WEYS').html();
	$("#JPY_WEYS").shuffleText(text, {
		frames   : shuffleFrame,    // Duration In ms (Milliseconds) Of Shuffle For Each Letter
		maxSpeed : shuffleSpeed,  // Max Duration In ms (Milliseconds) Of Global Shuffle
		amount   : 5,     // Amount Of Shuffle For Each Letter
		complete : chaffle_jpy1   // Do Something When Shuffle Is Completed
		});
	
}
function chaffle_jpy1(){
	var text = $('#JPY_NON').html();
	$("#JPY_NON").shuffleText(text, {
		frames   : shuffleFrame,    // Duration In ms (Milliseconds) Of Shuffle For Each Letter
		maxSpeed : shuffleSpeed,  // Max Duration In ms (Milliseconds) Of Global Shuffle
		amount   : 5,     // Amount Of Shuffle For Each Letter
		complete : chaffle_eur   // Do Something When Shuffle Is Completed
		});
}

function chaffle_eur(){
	var text = $('#EUR_WEYS').html();
	$("#EUR_WEYS").shuffleText(text, {
		frames   : shuffleFrame,    // Duration In ms (Milliseconds) Of Shuffle For Each Letter
		maxSpeed : shuffleSpeed,  // Max Duration In ms (Milliseconds) Of Global Shuffle
		amount   : 5,     // Amount Of Shuffle For Each Letter
		complete : chaffle_eur1   // Do Something When Shuffle Is Completed
		});
	
}
function chaffle_eur1(){
	var text = $('#EUR_NON').html();
	$("#EUR_NON").shuffleText(text, {
		frames   : shuffleFrame,    // Duration In ms (Milliseconds) Of Shuffle For Each Letter
		maxSpeed : shuffleSpeed,  // Max Duration In ms (Milliseconds) Of Global Shuffle
		amount   : 5,     // Amount Of Shuffle For Each Letter
		complete : chaffle_cny   // Do Something When Shuffle Is Completed
		});
}

function chaffle_cny(){
	var text = $('#CNY_WEYS').html();
	$("#CNY_WEYS").shuffleText(text, {
		frames   : shuffleFrame,    // Duration In ms (Milliseconds) Of Shuffle For Each Letter
		maxSpeed : shuffleSpeed,  // Max Duration In ms (Milliseconds) Of Global Shuffle
		amount   : 5,     // Amount Of Shuffle For Each Letter
		complete : chaffle_cny1   // Do Something When Shuffle Is Completed
		});
	
}
function chaffle_cny1(){
	var text = $('#CNY_NON').html();
	$("#CNY_NON").shuffleText(text, {
		frames   : shuffleFrame,    // Duration In ms (Milliseconds) Of Shuffle For Each Letter
		maxSpeed : shuffleSpeed,  // Max Duration In ms (Milliseconds) Of Global Shuffle
		amount   : 5,     // Amount Of Shuffle For Each Letter
		complete : null   // Do Something When Shuffle Is Completed
		});
}

function display_data(){
    $.ajax({
        type:"GET",
        url:"/weys/display",
        success : successResult,
        error : errorResult
    });
}

function successResult(result){
    var list = result.resultList;

    for(i=0 ; i<list.length ; i++){
        var unit = list[i].UNIT;
        var basicRate = numberWithCommas(list[i].BASIC_RATE);
        var cashBuy = numberWithCommas(list[i].CASH_BUY);
        var cashSell = numberWithCommas(list[i].CASH_SELL);
        
        if(unit == 'USD'){
            $('#USD_WEYS').html(cashBuy);
            $('#USD_NON').html(cashSell);
        } else if(unit == 'EUR'){
            $('#EUR_WEYS').html(cashBuy);
            $('#EUR_NON').html(cashSell);
        }  else if(unit == 'JPY'){
            $('#JPY_WEYS').html(cashBuy);
            $('#JPY_NON').html(cashSell);
        }  else if(unit == 'CNY'){
            $('#CNY_WEYS').html(cashBuy);
            $('#CNY_NON').html(cashSell);
        } 
    }
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function errorResult(result){
    
}
var canvas, stage, exportRoot, anim_container, dom_overlay_container, fnStartAnimation;
function init() {
	canvas = document.getElementById("canvas");
	anim_container = document.getElementById("animation_container");
	dom_overlay_container = document.getElementById("dom_overlay_container");
	var comp=AdobeAn.getComposition("11CCBE34E65B4DDBB00DC88E394B7690");
	var lib=comp.getLibrary();
	var loader = new createjs.LoadQueue(false);
	loader.addEventListener("fileload", function(evt){handleFileLoad(evt,comp)});
	loader.addEventListener("complete", function(evt){handleComplete(evt,comp)});
	var lib=comp.getLibrary();
	loader.loadManifest(lib.properties.manifest);
}
function handleFileLoad(evt, comp) {
	var images=comp.getImages();	
	if (evt && (evt.item.type == "image")) { images[evt.item.id] = evt.result; }	
}
function handleComplete(evt,comp) {
	//This function is always called, irrespective of the content. You can use the variable "stage" after it is created in token create_stage.
	var lib=comp.getLibrary();
	var ss=comp.getSpriteSheet();
	var queue = evt.target;
	var ssMetadata = lib.ssMetadata;
	for(i=0; i<ssMetadata.length; i++) {
		ss[ssMetadata[i].name] = new createjs.SpriteSheet( {"images": [queue.getResult(ssMetadata[i].name)], "frames": ssMetadata[i].frames} )
	}
	exportRoot = new lib.Untitled4();
	stage = new lib.Stage(canvas);
	stage.addChild(exportRoot);	
	//Registers the "tick" event listener.
	fnStartAnimation = function() {
		createjs.Ticker.setFPS(lib.properties.fps);
		createjs.Ticker.addEventListener("tick", stage);
	}	    
	//Code to support hidpi screens and responsive scaling.
	function makeResponsive(isResp, respDim, isScale, scaleType) {		
		var lastW, lastH, lastS=1;		
		window.addEventListener('resize', resizeCanvas);		
		resizeCanvas();		
		function resizeCanvas() {			
			var w = lib.properties.width, h = lib.properties.height;			
			var iw = window.innerWidth, ih=window.innerHeight;			
			var pRatio = window.devicePixelRatio || 1, xRatio=iw/w, yRatio=ih/h, sRatio=1;			
			if(isResp) {                
				if((respDim=='width'&&lastW==iw) || (respDim=='height'&&lastH==ih)) {                    
					sRatio = lastS;                
				}				
				else if(!isScale) {					
					if(iw<w || ih<h)						
						sRatio = Math.min(xRatio, yRatio);				
				}				
				else if(scaleType==1) {					
					sRatio = Math.min(xRatio, yRatio);				
				}				
				else if(scaleType==2) {					
					sRatio = Math.max(xRatio, yRatio);				
				}			
			}			
			canvas.width = w*pRatio*sRatio;			
			canvas.height = h*pRatio*sRatio;
			canvas.style.width = dom_overlay_container.style.width = anim_container.style.width =  w*sRatio+'px';				
			canvas.style.height = anim_container.style.height = dom_overlay_container.style.height = h*sRatio+'px';
			stage.scaleX = pRatio*sRatio;			
			stage.scaleY = pRatio*sRatio;			
			lastW = iw; lastH = ih; lastS = sRatio;		
		}
	}
	makeResponsive(false,'both',false,1);	
	AdobeAn.compositionLoaded(lib.properties.id);
	fnStartAnimation();

}
	
</script>
</head>
<body style="margin:0;overflow:hidden;">

	<div class="dp_body">
		<div class="dp_header"  id="animation_container">
			<canvas id="canvas" width="1080" height="600" style="position: absolute; display: block; background-color:rgba(255, 255, 255, 1.00);"></canvas>
			<div id="dom_overlay_container" style="pointer-events:none; overflow:hidden; width:1080px; height:600px; position: absolute; left: 0px; top: 0px; display: block;">
			</div>
		</div>
		
		<div class="dp_menu">
			<div class="dp_menu_tab1">CURRENCY</div>
			<div class="dp_menu_tab2">BUY</div>
			<div class="dp_menu_tab2">SELL</div>
		</div>
		
		<div class="dp_content">
			<div class="dp_cont_row" style="background-color:#ffffff;">
				<div class="dp_cont_unit">
					<div class="unit_img"><img src="${pageContext.request.contextPath}/resources/img/USD.png"></div>
					<div class="unit_text">USD</div>
				</div>
				<div class="dp_cont_rate">
					<div class="weys_rate" id="USD_WEYS">1,065.10</div>
					<div class="krw">KRW</div>
				</div>
				<div class="dp_cont_rate">
					<div class="non_rate" id="USD_NON">1,071.50</div>
					<div class="krw" style="padding-right:80px;">KRW</div>
				</div>
			</div>
			<div class="dp_cont_row" style="background-color:#f4f4f4;">
				<div class="dp_cont_unit">
					<div class="unit_img"><img src="${pageContext.request.contextPath}/resources/img/JPY.png"></div>
					<div class="unit_text" style="line-height: 50px;padding-top: 118px;">JPY<p style="font-size:32px;margin:0;">100</p></div>
				</div>
				<div class="dp_cont_rate">
					<div class="weys_rate"  id="JPY_WEYS">992.10</div>
					<div class="krw">KRW</div>
				</div>
				<div class="dp_cont_rate">
					<div class="non_rate" id="JPY_NON">996.20</div>
					<div class="krw" style="padding-right:80px;">KRW</div>
				</div>
			</div>
			<div class="dp_cont_row" style="background-color:#ffffff;">
				<div class="dp_cont_unit">
					<div class="unit_img"><img src="${pageContext.request.contextPath}/resources/img/EUR.png"></div>
					<div class="unit_text">EUR</div>
				</div>
				<div class="dp_cont_rate">
					<div class="weys_rate" id="EUR_WEYS"></div>
					<div class="krw">KRW</div>
				</div>
				<div class="dp_cont_rate">
					<div class="non_rate" id="EUR_NON">1,300</div>
					<div class="krw" style="padding-right:80px;">KRW</div>
				</div>
			</div>
			<div class="dp_cont_row" style="background-color:#f4f4f4;">
				<div class="dp_cont_unit">
					<div class="unit_img"><img src="${pageContext.request.contextPath}/resources/img/CNY.png"></div>
					<div class="unit_text">CNY</div>
				</div>
				<div class="dp_cont_rate">
					<div class="weys_rate" id="CNY_WEYS"></div>
					<div class="krw">KRW</div>
				</div>
				<div class="dp_cont_rate">
					<div class="non_rate" id="CNY_NON">135</div>
					<div class="krw" style="padding-right:80px;">KRW</div>
				</div>
			</div>
		</div>
		
		<div class="dp_footer">
			<div class="rate_dttm" id="title_dttm">2018-04-10 18:11</div>
			<div class="text_img"><img src="${pageContext.request.contextPath}/resources/img/img_footer_bi.png"></div>
		</div>
			
	</div>

</body>
</html>