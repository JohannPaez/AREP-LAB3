var apiclient = (function(){
	var zelda = "http://localhost:36000";
    return {
        loadDataAnimals: function(callback) {
            var promise = $.get({
        		url: "/animals"
            });
        	promise.then(function(data){
        		callback(null, JSON.parse(data));
        	}, function(error) {
        		callback(error, null);
        	});
		}, 
		addAnimal: function(jsonAnimal, callback) {		
		
			var promise = $.post({
        		url: "/addanimal",
        		data: JSON.stringify(jsonAnimal)
            });
        	promise.then(function(data){
        		callback(null, JSON.parse(data));
        	}, function(error) {
        		callback(error, null);
			});			
		}
    }
})();