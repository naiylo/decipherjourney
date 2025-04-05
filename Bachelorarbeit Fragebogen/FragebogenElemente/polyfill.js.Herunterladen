
/**
 * Shim for Array.indexOf
 * https://stackoverflow.com/a/3697563/336311
 */
if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function (elt) {
        var len = this.length >>> 0;

        var from = Number(arguments[1]) || 0;
        from = (from < 0)
                ? Math.ceil(from)
                : Math.floor(from);
        if (from < 0)
            from += len;

        for (; from < len; from++)
        {
            if (from in this &&
                    this[from] === elt)
                return from;
        }
        return -1;
    };
}


// Polyfill for CustomEvent
// source: https://developer.mozilla.org/en-US/docs/Web/API/CustomEvent/CustomEvent
(function() {
  if (typeof window.CustomEvent === "function") return false;

  function CustomEvent(event, params) {
    params = params || { bubbles: false, cancelable: false, detail: undefined };
    var evt = document.createEvent("CustomEvent");
    evt.initCustomEvent(event, params.bubbles, params.cancelable, params.detail);
    return evt;
  }

  CustomEvent.prototype = window.Event.prototype;

  window.CustomEvent = CustomEvent;
})();

// Polyfill for addEventListener IE 6+
// source: https://gist.github.com/eirikbacker/2864711
// addEventListener polyfill 1.0 / Eirik Backer / MIT Licence
(function(win, doc){
	if(win.addEventListener)return;		//No need to polyfill

	function docHijack(p){var old = doc[p];doc[p] = function(v){return addListen(old(v));};}
	function addEvent(on, fn, self){
		return (self = this).attachEvent('on' + on, function(e){
			var e = e || win.event;
			e.preventDefault  = e.preventDefault  || function(){e.returnValue = false;};
			e.stopPropagation = e.stopPropagation || function(){e.cancelBubble = true;};
                        if ("call" in fn) fn.call(self, e);
		});
	}
	function addListen(obj, i){
		if(i = obj.length)while(i--)obj[i].addEventListener = addEvent;
		else obj.addEventListener = addEvent;
		return obj;
	}

	addListen([doc, win]);
	if('Element' in win)win.Element.prototype.addEventListener = addEvent;			//IE8
	else{		//IE < 8
		doc.attachEvent('onreadystatechange', function(){addListen(doc.all);});		//Make sure we also init at domReady
		docHijack('getElementsByTagName');
		docHijack('getElementById');
		docHijack('createElement');
		addListen(doc.all);	
	}
})(window, document);


// Polyfill for Array.from()
// source: https://stackoverflow.com/a/62682524/336311

if (!Array.from) {
    /**
     * @param {Iterable} arr (required) - array-like or iterable object to convert it to an array.
     * @param {Callback} callbackFn function to call on every element of the array (optional)
     * @param {Object} thisArg value to use as this when executing callback (optional)
     * Return value - new Array instance
     *
     * The callbackFn argument usage is like in Array.map() callback.
     * The callbackFn function accepts the following arguments:
     *      @param "currentValue" (required) - the current element being processed in the array.
     *      @param "index" (optional) - the index of the current element being processed in the array.
     *      @param "array" (optional) - he array map was called upon.
     * Callback function that is called for every element of "arr". Each time callback executes, the returned value is added to new array ("arNew").
     */
    Array.from = function(arr, callbackFn, thisArg)
    {
        //if you need you can uncomment the following line
        //if(!arr || typeof arr == 'function')throw new Error('This function requires an array-like object - not null, undefined or a function');

        var arNew = [],
            k = [], // used for convert Set to an Array
            i = 0;

        //if you do not need a Set object support then
        //you can comment or delete the following if statement
        if(window.Set && arr instanceof Set)
        {
            //we use forEach from Set object
            arr.forEach(function(v){k.push(v)});
            arr = k;
        }

        for(; i < arr.length; i++)
            arNew[i] = callbackFn
                ? callbackFn.call(thisArg, arr[i], i, arr)
                : arr[i];

        return arNew;
    }
}