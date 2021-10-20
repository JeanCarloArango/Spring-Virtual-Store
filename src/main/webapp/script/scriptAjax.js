/* Variables Globales */

const ajaxCont = document.getElementById("ajax");
let alertSh = document.getElementById("alertCont");
let divContainer = document.getElementById("shQueries");
let timerWait = 120;
let timer = 0;

/* Funcion de recarga durante periodo inactivo */
window.addEventListener("click", () => {
	timer = 0;	
});

window.addEventListener("mousemove", () => {
	timer = 0;	
});

window.addEventListener("keypress", () => {
	timer = 0;	
});

let reloadInactive = function() {
	
	timer++;
	//console.log(timer);
	if(timer > timerWait) {
		window.onbeforeunload = function() {
			localStorage.log = "f";
		};
		location.reload();	
	}
	
	if (localStorage.log != "V") {
		window.location.href = "/BraveTeamApp";
	}
	
}

window.setInterval(reloadInactive, 1000);

// Cargar Formularios
const xhttpForms = new XMLHttpRequest;
xhttpForms.onload = function() {
	ajaxCont.innerHTML = this.responseText;
}

document.querySelectorAll(".link").forEach(function(el) {
	el.addEventListener("click", function(e) {
		let id = e.target.getAttribute("id");
		xhttpForms.open("GET", id + ".html", true);
		xhttpForms.send();
	});
});

// Nombre archivo subido
const prLink = document.getElementById("Products_mgmt");

prLink.addEventListener("click", function() {
	setTimeout(() => {
		const inBtn = document.getElementById("fileupload");
		const lblFile = document.getElementById("file-name");
		const senFile = document.getElementById("Button-sent");
		inBtn.addEventListener("change", () => {
			lblFile.textContent = inBtn.files[0].name;
		})
		senFile.addEventListener("click", () => {
			CargarArchivo();
		})
	}, 1000);
});

// file async 

async function CargarArchivo() {
	let formData = new FormData();
	formData.append("file", fileupload.files[0]);
	let response = await
		fetch('/BraveTeamApp/cargarArchivo', {
			method: "POST",
			body: formData
		});
	if (response.status == 200) {
		alert(response.responseText);
	}
};

// Eventos AJAX
const usrsLink = document.getElementById("CRUD_Users");
const cstmrLink = document.getElementById("CRUD_Customers");
const supLink = document.getElementById("CRUD_Suppliers");
const salesLink = document.getElementById("SalesModule");

usrsLink.addEventListener("click", () => {
	setTimeout(() => {
		const usrsSerBtn = document.getElementById("usrSerBtn");
		const usrsAddBtn = document.getElementById("usrAddBtn");
		const usrsUpBtn = document.getElementById("usrUpBtn");
		const usrsDelBtn = document.getElementById("usrDelBtn");
		usrsSerBtn.addEventListener("click", () => {
			submitSerUser();
		});
		usrsAddBtn.addEventListener("click", () => {
			submitCreateUser();
		});
		usrsUpBtn.addEventListener("click", () => {
			submitUpdateUser();
		});
		usrsDelBtn.addEventListener("click", () => {
			submitDelUser();
		});
	}, 1000);
});

cstmrLink.addEventListener("click", () => {
	setTimeout(() => {
		const cstmrSerBtn = document.getElementById("cstmrSerBtn");
		const cstmrAddBtn = document.getElementById("cstmrAddBtn");
		const cstmrUpBtn = document.getElementById("cstmrUpBtn");
		const cstmrDelBtn = document.getElementById("cstmrDelBtn");
		cstmrSerBtn.addEventListener("click", () => {
			submitSerCustomer();
		});
		cstmrAddBtn.addEventListener("click", () => {
			submitCreateCstmr();
		});
		cstmrUpBtn.addEventListener("click", () => {
			submitUpdateCstmr();
		});
		cstmrDelBtn.addEventListener("click", () => {
			submitDelCstmr();
		});
	}, 1000);
});

supLink.addEventListener("click", () => {
	setTimeout(() => {
		const supSerBtn = document.getElementById("supSerBtn");
		const supAddBtn = document.getElementById("supAddBtn");
		const supUpBtn = document.getElementById("supUpBtn");
		const supDelBtn = document.getElementById("supDelBtn");
		supSerBtn.addEventListener("click", () => {
			submitSerSup();
		});
		supAddBtn.addEventListener("click", () => {
			submitCreateSup();
		});
		supUpBtn.addEventListener("click", () => {
			submitUpdateSup();
		});
		supDelBtn.addEventListener("click", () => {
			submitDelSup();
		});
	}, 1000);
});

salesLink.addEventListener("click", () => {
	setTimeout(() => {
		const salesSerCstmr = document.getElementById("btnCstmrSer");
		const salesSerPr = document.getElementById("btnCons");
		const sendSales = document.getElementById("btnBuy");
		salesSerCstmr.addEventListener("click", () => {
			serCstmrSales();
		});
		salesSerPr.addEventListener("click", () => {
			serPrSales();
		});
		sendSales.addEventListener("click", () => {
			submitSales();
		});
	}, 1000);
});

// Creación tabla de consultas
function CreateTableFromJSON(json_result) {

	const json_arr = JSON.parse(json_result);

	// EXTRACT VALUE FOR HTML HEADER. 	
	let col = [];
	for (let i = 0; i < json_arr.length; i++) {
		for (let key in json_arr[i]) {
			if (col.indexOf(key) === -1) {
				col.push(key);
			}
		}
	}

	// CREATE DYNAMIC TABLE.
	let table = document.createElement("table");
	table.setAttribute("border", "1");

	// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

	let tr = table.insertRow(-1); // TABLE ROW.


	for (let i = 0; i < col.length; i++) {
		let th = document.createElement("th"); // TABLE HEADER.
		th.innerHTML = col[i];
		tr.appendChild(th);
	}

	// ADD JSON DATA TO THE TABLE AS ROWS.
	for (let i = 0; i < json_arr.length; i++) {

		tr = table.insertRow(-1);

		for (let j = 0; j < col.length; j++) {
			let tabCell = tr.insertCell(-1);
			tabCell.innerHTML = json_arr[i][col[j]];
		}
	}

	// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
	divContainer.innerHTML = "";
	divContainer.appendChild(table);
	//alert(divContainer);

}

// Validaciones

// Usuarios
function validateUsr() {
	const usrDni = document.getElementById("txtDni");
	const usrName = document.getElementById("txtName");
	const usrEmail = document.getElementById("txtEmail");
	const usrNick = document.getElementById("txtUsr");
	const usrPass = document.getElementById("txtPass");

	hideErrors();
	if (usrDni.value.trim().length == 0) {
		usrDni.focus();
		shErrors("Cédula no puede estar vacío");
		return false;
	} else if (usrName.value.trim().length == 0) {
		usrName.focus();
		shErrors("Nombre no puede estar vacío");
		return false;
	} else if (usrEmail.value.trim().length == 0) {
		usrEmail.focus();
		shErrors("E-mail no puede estar vacío");
		return false;
	} else if (usrNick.value.trim().length == 0) {
		usrNick.focus();
		shErrors("Usuario no puede estar vacío");
		return false;
	} else if (usrPass.value.trim().length == 0) {
		usrPass.focus();
		shErrors("Contraseña no puede estar vacío");
		return false;
	} else {
		return true;
	}

}

// Clientes
function validateCstmr() {
	const cstmrDni = document.getElementById("txtDni");
	const cstmrName = document.getElementById("txtName");
	const cstmrAddr = document.getElementById("txtAddr");
	const cstmrPhone = document.getElementById("txtPhone");
	const cstmrEmail = document.getElementById("txtEmail");

	hideErrors();
	if (cstmrDni.value.trim().length == 0) {
		cstmrDni.focus();
		shErrors("Cédula no puede estar vacío");
		return false;
	} else if (cstmrName.value.trim().length == 0) {
		cstmrName.focus();
		shErrors("Nombre no puede estar vacío");
		return false;
	} else if (cstmrAddr.value.trim().length == 0) {
		cstmrAddr.focus();
		shErrors("Dirección no puede estar vacío");
		return false;
	} else if (cstmrPhone.value.trim().length == 0) {
		cstmrPhone.focus();
		shErrors("Teléfono no puede estar vacío");
		return false;
	} else if (cstmrEmail.value.trim().length == 0) {
		cstmrEmail.focus();
		shErrors("E-mail no puede estar vacío");
		return false;
	} else {
		return true;
	}

}

// Proveedores
function validateSup() {
	const supNit = document.getElementById("txtNit");
	const supName = document.getElementById("txtName");
	const supAddr = document.getElementById("txtAddr");
	const supPhone = document.getElementById("txtPhone");
	const supCity = document.getElementById("txtCity");

	hideErrors();
	if (supNit.value.trim().length == 0) {
		supNit.focus();
		shErrors("NIT no puede estar vacío");
		return false;
	} else if (supName.value.trim().length == 0) {
		supName.focus();
		shErrors("Nombre no puede estar vacío");
		return false;
	} else if (supAddr.value.trim().length == 0) {
		supAddr.focus();
		shErrors("Dirección no puede estar vacío");
		return false;
	} else if (supPhone.value.trim().length == 0) {
		supPhone.focus();
		shErrors("Teléfono no puede estar vacío");
		return false;
	} else if (supCity.value.trim().length == 0) {
		supCity.focus();
		shErrors("Ciudad no puede estar vacío");
		return false;
	} else {
		return true;
	}

}

// Errors Dialogs
function shErrors(txtContent) {
	alertSh.innerHTML = "";
	let errorCont = document.createElement("div");
	errorCont.classList.add("error-cont");
	let errorMsg = document.createElement("span");
	errorMsg.classList.add("error-msg");
	let content = document.createTextNode(txtContent);
	errorMsg.appendChild(content);
	errorCont.appendChild(errorMsg);
	alertSh.appendChild(errorCont);
}

function hideErrors() {
	const inputs = document.querySelectorAll(".txt");
	// console.log(inputs);
	inputs.forEach((input) => {
		input.addEventListener("input", () => {
			alertSh.innerHTML = "";
		});
	});
}

function hideTable() {
	const inputs = document.querySelectorAll(".txt");
	// console.log(inputs);
	inputs.forEach((input) => {
		input.addEventListener("input", () => {
			divContainer.innerHTML = "";
		});
	});
}

// Success Dialogs
function shSuccess(txtContent) {
	alertSh.innerHTML = "";
	let successCont = document.createElement("div");
	successCont.classList.add("success-cont");
	let successMsg = document.createElement("span");
	successMsg.classList.add("success-msg");
	let content = document.createTextNode(txtContent);
	successMsg.appendChild(content);
	successCont.appendChild(successMsg);
	alertSh.appendChild(successCont);
	document.querySelectorAll(".txt").forEach((el) => {
		el.value = "";
	});
}

// Submit Forms

// Usuarios
function submitSerUser() {
	const usrDni = document.getElementById("txtDni").value.trim();
	const xhttpServer = new XMLHttpRequest();

	var url = '/BraveTeamApp/buscarUsuario';
	var params = "cedula=" + usrDni;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			//alert(xhttpServer.responseText);
			if (xhttpServer.responseText === "[]") {
				shErrors("Usuario con cedula " + usrDni + " no existe");
				hideErrors();
				return false;
			} else {
				shSuccess("Usuario encontrado");
				CreateTableFromJSON(xhttpServer.responseText);
				hideTable();
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			}
		} else {
			shErrors("Datos no enviados");
		}
	}

	xhttpServer.send(params);

	return;

}

function submitCreateUser() {
	const usrDni = document.getElementById("txtDni").value.trim();
	const usrName = document.getElementById("txtName").value.trim();
	const usrEmail = document.getElementById("txtEmail").value.trim();
	const usrNick = document.getElementById("txtUsr").value.trim();
	const usrPass = document.getElementById("txtPass").value.trim();
	const xhttpServer = new XMLHttpRequest();
	let valid = validateUsr();
	if (valid) {
		var url = '/BraveTeamApp/crearUsuario';

		var params = "userDni=" + usrDni + "&" + "userName=" + usrName + "&" + "userEmail=" + usrEmail + "&" + "userNick=" + usrNick + "&" + "userPass=" + usrPass;
		xhttpServer.open('POST', url, true);

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Usuario creado satisfactoriamente");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados");
			}
		}

		xhttpServer.send(params);

	}

	return;

}

function submitUpdateUser() {
	const usrDni = document.getElementById("txtDni").value.trim();
	const usrName = document.getElementById("txtName").value.trim();
	const usrEmail = document.getElementById("txtEmail").value.trim();
	const usrNick = document.getElementById("txtUsr").value.trim();
	const usrPass = document.getElementById("txtPass").value.trim();
	const xhttpServer = new XMLHttpRequest();
	let valid = validateUsr();
	if (valid) {
		var url = '/BraveTeamApp/actualizarUsuario';
		var params = "userDni=" + usrDni + "&" + "userName=" + usrName + "&" + "userEmail=" + usrEmail + "&" + "userNick=" + usrNick + "&" + "userPass=" + usrPass;
		xhttpServer.open('POST', url, true);

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Usuario actualizado con éxito");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados");
			}
		}

		xhttpServer.send(params);

	}

	return;

}

function submitDelUser() {
	const usrDni = document.getElementById("txtDni").value.trim();
	const xhttpServer = new XMLHttpRequest();

	var url = '/BraveTeamApp/eliminarUsuario';
	var params = "cedula=" + usrDni;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			shSuccess("Usuario eliminado con éxito");
			setTimeout(() => {
				alertSh.innerHTML = "";
			}, 4000);
		} else {
			shErrors("Datos no enviados");
		}
	}

	xhttpServer.send(params);

	return;

}

// Clientes

function submitSerCustomer() {
	const usrDni = document.getElementById("txtDni").value.trim();
	const xhttpServer = new XMLHttpRequest();

	var url = '/BraveTeamApp/buscarCliente';
	var params = "cedula=" + usrDni;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			//alert(xhttpServer.responseText);
			if (xhttpServer.responseText === "[]") {
				shErrors("Cliente con cedula " + usrDni + " no existe");
				hideErrors();
				return false;
			} else {
				shSuccess("Cliente encontrado");
				CreateTableFromJSON(xhttpServer.responseText);
				hideTable();
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			}
		} else {
			shErrors("Datos no enviados");
		}
	}

	xhttpServer.send(params);

	return;

}

function submitCreateCstmr() {
	const cstmrDni = document.getElementById("txtDni").value.trim();
	const cstmrName = document.getElementById("txtName").value.trim();
	const cstmrAddr = document.getElementById("txtAddr").value.trim();
	const cstmrPhone = document.getElementById("txtPhone").value.trim();
	const cstmrEmail = document.getElementById("txtEmail").value.trim();
	const xhttpServer = new XMLHttpRequest();
	let valid = validateCstmr();
	if (valid) {
		var url = '/BraveTeamApp/crearCliente';
		var params = "identifyCustomer=" + cstmrDni + "&" + "nameCustomer=" + cstmrName + "&" + "addressCustomer=" + cstmrAddr + "&" + "phoneCustomer=" + cstmrPhone + "&" + "emailCustomer=" + cstmrEmail;
		xhttpServer.open('POST', url, true);

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Cliente creado satisfactoriamente");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados");
			}
		}

		xhttpServer.send(params);

	}

	return;

}

function submitUpdateCstmr() {
	const cstmrDni = document.getElementById("txtDni").value.trim();
	const cstmrName = document.getElementById("txtName").value.trim();
	const cstmrAddr = document.getElementById("txtAddr").value.trim();
	const cstmrPhone = document.getElementById("txtPhone").value.trim();
	const cstmrEmail = document.getElementById("txtEmail").value.trim();
	const xhttpServer = new XMLHttpRequest();
	let valid = validateCstmr();
	if (valid) {
		var url = '/BraveTeamApp/actualizarCliente';
		var params = "identifyCustomer=" + cstmrDni + "&" + "nameCustomer=" + cstmrName + "&" + "addressCustomer=" + cstmrAddr + "&" + "phoneCustomer=" + cstmrPhone + "&" + "emailCustomer=" + cstmrEmail;
		xhttpServer.open('POST', url, true);

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Cliente actualizado satisfactoriamente");
				setTimeout(function() {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados");
			};
		}

		xhttpServer.send(params);

	}

	return;

}

function submitDelCstmr() {
	const cstmrDni = document.getElementById("txtDni").value.trim();
	const xhttpServer = new XMLHttpRequest();
	var url = '/BraveTeamApp/eliminarCliente';
	var params = "cedula=" + cstmrDni;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			shSuccess("Cliente eliminado satisfactoriamente");
			setTimeout(() => {
				alertSh.innerHTML = "";
			}, 4000);
		} else {
			shErrors("Datos no enviados.");
		}
	}

	xhttpServer.send(params);

	return;

}

// Proveedores
function submitSerSup() {
	const supNit = document.getElementById("txtNit").value.trim();
	const xhttpServer = new XMLHttpRequest();

	var url = '/BraveTeamApp/buscarProveedor';
	var params = "nit=" + supNit;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			if (xhttpServer.responseText === "[]") {
				shErrors("Proveedor con nit " + supNit + " no existe");
				hideErrors();
				return false;
			} else {
				shSuccess("Proveedor encontrado");
				CreateTableFromJSON(xhttpServer.responseText);
				hideTable();
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			}
		} else {
			shErrors("Datos no enviados");
		}
	}

	xhttpServer.send(params);

	return;

}

function submitCreateSup() {
	const supNit = document.getElementById("txtNit").value.trim();
	const supName = document.getElementById("txtName").value.trim();
	const supAddr = document.getElementById("txtAddr").value.trim();
	const supPhone = document.getElementById("txtPhone").value.trim();
	const supCity = document.getElementById("txtCity").value.trim();
	const xhttpServer = new XMLHttpRequest();
	let valid = validateSup();
	if (valid) {
		var url = '/BraveTeamApp/crearProveedor';
		var params = "supplierNit=" + supNit + "&" + "supplierName=" + supName + "&" + "supplierAddress=" + supAddr + "&" + "supplierPhone=" + supPhone + "&" + "supplierCity=" + supCity;
		xhttpServer.open('POST', url, true);

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Proveedor creado satisfactoriamente");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			}
		}

		xhttpServer.send(params);

	}

	return;

}

function submitUpdateSup() {
	const supNit = document.getElementById("txtNit").value.trim();
	const supName = document.getElementById("txtName").value.trim();
	const supAddr = document.getElementById("txtAddr").value.trim();
	const supPhone = document.getElementById("txtPhone").value.trim();
	const supCity = document.getElementById("txtCity").value.trim();
	const xhttpServer = new XMLHttpRequest();
	let valid = validateSup();
	if (valid) {
		var url = '/BraveTeamApp/actualizarProveedor';
		var params = "supplierNit=" + supNit + "&" + "supplierName=" + supName + "&" + "supplierAddress=" + supAddr + "&" + "supplierPhone=" + supPhone + "&" + "supplierCity=" + supCity;
		xhttpServer.open('POST', url, true);

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Proveedor actualizado satisfactoriamente");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados");
			}
		}

		xhttpServer.send(params);

	}

	return;

}

function submitDelSup() {
	const supNit = document.getElementById("txtNit").value.trim();
	const xhttpServer = new XMLHttpRequest();
	var url = '/BraveTeamApp/eliminarProveedor';
	var params = "nit=" + supNit;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			shSuccess("Proveedor eliminado satisfactoriamente");
			setTimeout(() => {
				alertSh.innerHTML = "";
			}, 4000);
		} else {
			shErrors("Datos no enviados");
		}
	}

	xhttpServer.send(params);

	return;

}

/* sales Module */

let values = [];
let tokens = [];

function getJsonCstmr(json_result) {
	const json_arr = JSON.parse(json_result);
	const lblCstmr = document.getElementById("cstmrLbl");
	// alert(json_result);
	let rs = "";
	let id = "";

	let col = [];
	for (let i = 0; i < json_arr.length; i++) {
		for (let key in json_arr[i]) {
			if (col.indexOf(key) === -1) {
				col.push(key);
			}
		}
	}
	
	for (let i = 0; i < json_arr.length; i++) {
		for (let j = 0; j < col.length; j++) {
			rs = json_arr[i][col[3]];
			id = json_arr[i][col[0]];
		}
	}
		
	lblCstmr.innerHTML = rs;
	
	tokens.push(id);
	
}

function getJsonPr(json_result) {
	const json_arr = JSON.parse(json_result);
	const lblPr = document.getElementById("lblProduct");
	let rsVal;
	let rsName;

	let col = [];
	for (let i = 0; i < json_arr.length; i++) {
		for (let key in json_arr[i]) {
			if (col.indexOf(key) === -1) {
				col.push(key);
			}
		}
	}
	
	for (let i = 0; i < json_arr.length; i++) {
		for (let j = 0; j < col.length; j++) {
			rsVal = json_arr[i][col[2]];
		}
	}

	for (let i = 0; i < json_arr.length; i++) {
		for (let j = 0; j < col.length; j++) {
			rsName = json_arr[i][col[1]];
		}
	}
	
	calcProducts(rsVal);
	lblPr.innerHTML = rsName + " c/u " + "$" + rsVal;
	
}

function serCstmrSales() {
	const cstmrDni = document.getElementById("txtDniSer").value.trim();
	const xhttpServer = new XMLHttpRequest();

	var url = '/BraveTeamApp/buscarCliente';
	var params = "cedula=" + cstmrDni;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			//alert(xhttpServer.responseText);
			if (xhttpServer.responseText === "[]") {
				shErrors("Cliente con cedula " + cstmrDni + " no existe");
				hideErrors();
				return false;
			} else {
				shSuccess("Cliente encontrado");
				getJsonCstmr(xhttpServer.responseText);
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			}
		} else {
			shErrors("Datos no enviados");
		}
	}

	xhttpServer.send(params);

	return;

}

function serPrSales() {
	const prName = document.getElementById("txtPrName").value.trim();
	const xhttpServer = new XMLHttpRequest();

	var url = '/BraveTeamApp/buscarProductos';
	var params = "name=" + prName;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			// alert(xhttpServer.responseText);
			if (xhttpServer.responseText === "[]") {
				shErrors("Producto " + prName + " no existe");
				hideErrors();
				return false;
			} else {
				getJsonPr(xhttpServer.responseText);
				shSuccess("Producto encontrado");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			}
		} else {
			shErrors("Datos no enviados");
		}
	}

	xhttpServer.send(params);

	return;

}

function calcProducts(valPr) {
	const lblVal = document.getElementById("valT");
	const lblSaleTotal = document.getElementById("saleTotal");
	const lblValIVA = document.getElementById("valIVA");
	const lblFinalTot = document.getElementById("finalTotal");
	const prCant = document.getElementById("txtCant");
	let res = 0;
	
	let valT = prCant.value.trim() * valPr;
	
	lblVal.innerHTML = "Cant: " + prCant.value.trim() + "<br>" + "$" + valT;
	
	values.push(valT);
	
	for(let i = 0; i < values.length; i++) {
		res += values[i];
	}
			
	lblSaleTotal.innerHTML = "$" + res;
	
	let valIVA = res * 0.19;
	
	lblValIVA.innerHTML = "$" + valIVA;
	
	let totSale = res + valIVA;
	
	lblFinalTot.innerHTML = "$" + totSale;
	
	tokens.push(res);
	tokens.push(valIVA);
	tokens.push(totSale);
		
}

function submitSales() {
	const xhttpServer = new XMLHttpRequest();
	
	var url = '/BraveTeamApp/crearVenta';
	var params = "customer=" + tokens[0] + "&ivaSale=" + tokens[2] + "&totalSale=" + tokens[1] + "&valorFinal=" + tokens[3];
	xhttpServer.open('POST', url, true);
	
	alert(tokens);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			shSuccess("Venta Creada Exitosamente");
			setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
		} else {
			shErrors("Datos no enviados");
		}
	}

	xhttpServer.send(params);
	
}

// Logs Logic

const logsLink = document.getElementById("Logs");
const empty = "";

logsLink.addEventListener("click", function() {
	setTimeout(() => {		
		const usrLink = document.getElementById("usrList");
		const cstmrLink = document.getElementById("cstmrList");
		const salesLink = document.getElementById("saleList");
		
		usrLink.addEventListener("click", function() {
			usersList();
		});
		cstmrLink.addEventListener("click", function() {
			customersList();
		});
		salesLink.addEventListener("click", function() {
			salesList();
		});

	}, 1000);
});

function usersList() {
	const xhttpServer = new XMLHttpRequest();

	var url = '/BraveTeamApp/buscarUsuario';
	var params = "cedula=" + empty;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			//alert(xhttpServer.responseText);
			shSuccess("Listado de Usuarios");
			CreateTableFromJSON(xhttpServer.responseText);
			setTimeout(() => {
				alertSh.innerHTML = "";
			}, 4000);
		} else {
			shErrors("Datos no enviados");
		}
	}

	xhttpServer.send(params);

	return;

}

function customersList() {
	const xhttpServer = new XMLHttpRequest();

	var url = '/BraveTeamApp/buscarCliente';
	var params = "cedula=" + empty;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			//alert(xhttpServer.responseText);
			shSuccess("Listado Clientes");
			CreateTableFromJSON(xhttpServer.responseText);
			setTimeout(() => {
				alertSh.innerHTML = "";
			}, 4000);
		} else {
			shErrors("Datos no enviados");
		}
	}

	xhttpServer.send(params);

	return;

}

function salesList() {
	const xhttpServer = new XMLHttpRequest();
	
	var params = "";

	var url = '/BraveTeamApp/buscarDetVentas';
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
		if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
			//alert(xhttpServer.responseText);
			shSuccess("Listado Ventas");
			CreateTableFromJSON(xhttpServer.responseText);
			setTimeout(() => {
				alertSh.innerHTML = "";
			}, 4000);
		} else {
			shErrors("Datos no enviados");
		}
	}
	
	xhttpServer.send(params);

	return;

}