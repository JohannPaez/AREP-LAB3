var app = (function () {

    var tableConstructor = function(error, data) {
        if (error != null) {
            alert("Ocurrio un error al cargar los datos");
            return;
        }
        var stringTable = table(data);
        $("#idTable").html(stringTable);
    }

    var table = function(dataTable) {
		var tabla = "<center> <table class='table table-bordered table-dark' style = 'width:50%; text-align: center;'" +
						"<thead>" +
							"<tr>" +
								"<th scope='col'> Animal </th>" +
								"<th scope='col'> Nombre </th>" +
								"<th scope='col'> Edad </th>" +
							"</tr>" +
						"</thead>" +
                        "<tbody>";
            dataTable.forEach(function(animal) {
                tabla += "<tr>" +
                            "<td>" + animal.animal + "</td>" +
                            "<td>" + animal.nombre + "</td>" +
                            "<td>" + animal.edad + "</td>" +
                        "</tr>";
            });
        
        tabla += "</tbody> </table> </center>";
        return tabla;    
    }

    var createAnimal = function() {
        var animal = $("#idAnimal").val();
        var nombre = $("#idNombre").val();
        var edad = $("#idEdad").val();
        var jsonAnimal = {"animal": animal, "nombre": nombre, "edad": edad}; 
        console.log(jsonAnimal);
        console.log(JSON.stringify(jsonAnimal));
        flag = false;
        apiclient.addAnimal(jsonAnimal, showAll);
        if (flag == true) return;
        showAll(null, "");
    }

    var flag;
    var showAll = function(error, datos) {
        if(error != null){
            alert("Error, verifique que la entrada tiene el formato establecido.");
            flag = true;
            return;
        }
        apiclient.loadDataAnimals(tableConstructor);
        refresAll();
    }

    var refresAll = function() {
        $("#idAnimal").val("");
        $("#idNombre").val("");
        $("#idEdad").val("");
    }


    return {
        loadTableAnimals: function() {
            apiclient.loadDataAnimals(tableConstructor);
        },
        addAnimal: function() {
            createAnimal();
        }
    }
})();