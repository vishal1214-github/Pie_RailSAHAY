/*!
 * jquery.numscroll.js (Digital rolling cumulative animation)
 * version 3.0.0
 * 2019-08-08
 */

(function($) {
	
	$.fn.numScroll = function(options) {
		var settings = $.extend({
			'number': '0', 
			'step': 1, 
			'time': 10000,
			'delay': 0,
			'symbol': false ,
			'fromZero': true, 
		}, options);
		settings.number = settings.number.toString();
		
		return this.each(function(){
			var $this = $(this),
				oldNum = $this.text() || '0';
			if (settings.number.indexOf(',') > 0) {
				settings.symbol = true;
			}
			if (options && options.symbol===false) {
				settings.symbol = false;
			}
			var targetNum = settings.number.replace(/,/g, '') || 0,
				oldRealNum = oldNum.replace(/,/g, '');
			if(settings.symbol){
				$this.text(oldNum);
			}else{
				$this.text(oldRealNum);
			}
			if (settings.fromZero) {
				oldRealNum = 0;
			}
			if(isNaN(oldRealNum)){
				oldRealNum = 0;
			}
			if(isNaN(targetNum)){
				return;
			}
			targetNum = parseFloat(targetNum);
			oldRealNum= parseFloat(oldRealNum);
			var tempNum = oldRealNum,
				numIsInt = isInt(targetNum),
				numIsFloat = isFloat(targetNum),
				step = !settings.time?1:Math.abs(targetNum-oldRealNum) * 10 / settings.time,
				numScroll;
			function numInitUpdate() {
				var showNum = '';
				if (numIsInt) {
					showNum = Math.floor(tempNum);
				} else if (numIsFloat != -1) {
					showNum = tempNum.toFixed(numIsFloat)
				} else {
					showTarget(targetNum);
					clearInterval(numScroll);
					return;
				}
				if (settings.symbol) {
					showNum = formatSymbol(showNum);
				}
				$this.text(showNum);
			}
			
			function showTarget(num) {
				var targetNum = num.toString().replace(/,/g, '');
				if (settings.symbol) {
					targetNum = formatSymbol(targetNum);
				}
				$this.text(targetNum);
			}
			
			setTimeout(function() {
				numScroll = setInterval(function() {
					numInitUpdate();
					if(oldRealNum < targetNum){
						tempNum += step;
						if (tempNum > targetNum) {
							showTarget(targetNum);
							clearInterval(numScroll);
						}
					}else{
						tempNum -= step;
						if (tempNum < targetNum) {
							showTarget(targetNum);
							clearInterval(numScroll);
						}
					}
				}, 1);
			}, settings.delay);
			
		})
	};

	
	function isInt(num) {
		var res = false;
		try {
			if (String(num).indexOf(".") == -1 && String(num).indexOf(",") == -1) {
				res = parseInt(num) % 1 === 0 ? true : false;
			}
		} catch (e) {
			res = false;
		}
		return res;
	}
	function isFloat(num) {
		var res = -1;
		try {
			if (String(num).indexOf(".") != -1) {
				var index = String(num).indexOf(".") + 1; 
				var count = String(num).length - index; 
				if (index > 0) {
					res = count;
				}
			}
		} catch (e) {
			res = -1;
		}
		return res;
	}
	function formatSymbol(num) {
		var res = '';
		var str = num + '',
			strLeft = '',
			strRight = '';
		var floatNum = isFloat(num);
		if (floatNum != -1) {
			var splitStr = str.split('.');
			strLeft = splitStr[0];
			strRight = splitStr[1];
		} else {
			strLeft = str;
		}
		res = strLeft.split("").reverse().reduce(function(prev, next, index) {
			return ((index % 3) ? next : (next + ',')) + prev;
		})
		if (strRight != '') {
			res = res + '.' + strRight;
		}
		return res;
	}
})(jQuery);