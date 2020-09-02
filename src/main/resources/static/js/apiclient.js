var apiclient = (function(){

    return {
        realizarCalculos: function(datos, callback) {
            var promise = $.post({
        		url: "/operation",
        		data: JSON.stringify(datos),
        		contentType: "application/json"
            });
        	promise.then(function(data){
        		callback(null, JSON.parse(data));
        	}, function(error) {
        		callback(error, null);
        	});
        }
    }
})();