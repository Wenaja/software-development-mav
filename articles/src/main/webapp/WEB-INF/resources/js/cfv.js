/**
 * @name Form-Validator {http://www.software-development.net}
 *
 * @version 1.0.0
 * @author Rempel, Juri [juri.rempel@software-developemnt.net]
 * @copyright Rempel, Juri Oktober 2018
 * @description JavaScript zur Validierung von Formulardaten. Es wird 'Strategy-Pattern' verwendet. Method 'chekValidity()' des Validators prueft Benutzerangaben. Konkrete Implimentierung von 'checkValidity' wird zur Laufzeit (startegy pettern) dynamisch gewaehlt
 *
 */
var regexs = {};
var v = {};
var _validator;
var counter = 0;


var Validator = function () {
	this.inputField = "";
};

Validator.prototype = {
	setStrategy: function (inputField) {
		this.inputField = inputField;
	},

	checkValidity: function (regex, id) {
		return this.inputField.checkValidity(regex, id);
	}
};

var DUMMY = function () {
	this.checkValidity = function (regex, id) {
		// Keine Funktionalitaet, nur als Platzhalter
	}
};

var FORENAME = function () {
	this.checkValidity = function (regex, id) {
		var val = document.getElementById('frm_createNewAccount01:inpf_forename01').value;

		if (val == "") {
			return;
		}

		if (regex.test(val)) {
			document.getElementsByName('message')[1].innerHTML = 'Ok';
		} else {
			document.getElementsByName('message')[1].innerHTML = 'Vorname darf nur Kleinbuchstaben (a-z), Großbuchstaben (A-Z) und Leerzeichen enthalten';
			document.getElementById('frm_createNewAccount01:inpf_forename01').focus();
		}
	}
};

var SURNAME = function () {
	this.checkValidity = function (regex, id) {
		var val = document.getElementById('frm_createNewAccount01:inpf_surname01').value;

		if (val == "") {
			return;
		}

		if (regex.test(val)) {
			document.getElementsByName('message')[2].innerHTML = 'Ok';
		} else {
			document.getElementsByName('message')[2].innerHTML = 'Nachname darf nur Kleinbuchstaben (a-z), Großbuchstaben (A-Z) und Bindestriche (-) enthalten';
			document.getElementById('frm_createNewAccount01:inpf_surname01').focus();
		}
	}
};

var USERNAME = function () {
	this.checkValidity = function (regex, id) {
		var val = document.getElementById('frm_createNewAccount01:inpf_username01').value;

		if (val == "") {
			document.getElementsByName('message')[3].innerHTML = 'Benutzername darf nur Kleinbuchstaben (a-z), Großbuchstaben (A-Z), Ziffer, underlines (_) und Bindestriche (-) enthalten';
			document.getElementById('frm_createNewAccount01:inpf_username01').focus();
			return;
		}

		if (regex.test(val)) {
			document.getElementsByName('message')[3].innerHTML = 'Ok';
		} else {
			document.getElementsByName('message')[3].innerHTML = 'Benutzername darf nur Kleinbuchstaben (a-z), Großbuchstaben (A-Z), Ziffer, underlines (_) und Bindestriche (-) enthalten';
			document.getElementById('frm_createNewAccount01:inpf_username01').focus();
		}
	}
};

var EMAIL = function () {
	this.checkValidity = function (regex, id) {
		var val = document.getElementById('frm_createNewAccount01:inpf_email01').value;

		if (val == "") {
			document.getElementById('frm_createNewAccount01:inpf_email01').focus();
			return;
		}


		if (regex.test(val)) {
			document.getElementsByName('message')[4].innerHTML = 'Ok';
		} else {
			document.getElementsByName('message')[4].innerHTML = 'Dies ist keine gültige E-mail Adresse';
			document.getElementById('frm_createNewAccount01:inpf_email01').focus();
		}
	}
};

var PWD = function () {
	this.checkValidity = function (regex, id) {
		var val = "";
		var indx = -1;
		//var elem = '';

		if (id == "frm_createNewAccount01:inpf_pwd02") {
			val = document.getElementById('frm_createNewAccount01:inpf_pwd01').value;
			indx = 5;
			//elem = "frm_createNewAccount01:inpf_pwd01";
		} else if (id == "frm_createNewAccount01:btn_submit01") {
			val = document.getElementById('frm_createNewAccount01:inpf_pwd02').value;
			indx = 6;
			//elem = "frm_createNewAccount01:inpf_pwd02";
		} else {
			val = "";
			indx = -1;
			//elem = '';
		}

		if (val == "") {
			document.getElementById(elem).focus();
			return;
		}

		if (regex.test(val)) {
			document.getElementsByName('message')[indx].innerHTML = 'Ok';
		} else {
			document.getElementsByName('message')[indx].innerHTML = 'Das Kennwort muss mindestens sechs Zeichen lang sein und: mindestens einen kleinen, einen großen Buchstaben enthalten und dazu noch eine Ziffer';
			document.getElementById(elem).focus();
			//document.getElementsByName('message')[1].innerHTML = elem;
		}

	}
};


function initialize() {
	regexs['frm_createNewAccount01:inpf_forename01'] = /^[a-zA-Z ]$/;
	regexs['frm_createNewAccount01:inpf_surname01'] = /^[a-zA-Z -]{3,30}$/;
	regexs['frm_createNewAccount01:inpf_username01'] = /^[a-zA-Z ]{3,30}$/;
	regexs['frm_createNewAccount01:inpf_email01'] = /^[a-zA-Z0-9_-]{3,30}$/;
	regexs['frm_createNewAccount01:inpf_pwd01'] = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	regexs['frm_createNewAccount01:inpf_pwd02'] = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z_]{6,30}$/;
	regexs['frm_createNewAccount01:btn_submit01'] = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z_]{6,30}$/;

	v['frm_createNewAccount01:inpf_forename01'] = new DUMMY();
	v['frm_createNewAccount01:inpf_surname01'] = new FORENAME();
	v['frm_createNewAccount01:inpf_username01'] = new SURNAME();
	v['frm_createNewAccount01:inpf_email01'] = new USERNAME();
	v['frm_createNewAccount01:inpf_pwd01'] = new EMAIL();
	v['frm_createNewAccount01:inpf_pwd02'] = new PWD();
	v['frm_createNewAccount01:btn_submit01'] = new PWD();

	_validator = new Validator();

};

function validate(elem) {
	if (_validator == undefined) {
		initialize();
	}

	//document.getElementsByName('message')[0].innerHTML = regexs[elem.id];
	_validator.setStrategy(v[elem.id]);
	_validator.checkValidity(regexs[elem.id], elem.id);

};

function setupSubmit() {
	/*
	 * 	1. check if hidden is still true
	 * 	2. vergleiche pwds
	 * 	2. encode pwd fields
	 */
	
	var pwd01 = document.getElementById('frm_createNewAccount01:inpf_pwd01').value;
	var pwd02 = document.getElementById('frm_createNewAccount01:inpf_pwd02').value;

	if(pwd01 == "") {
		document.getElementsByName('message')[5].innerHTML = "Dieses Feld darf nicht leer bleiben";
		return false;
	}

	if (pwd01 == pwd02) {
		document.getElementById('frm_createNewAccount01:inpf_pwd01').value = sha256(pwd01);
		document.getElementById('frm_createNewAccount01:inpf_pwd02').value = sha256(pwd02);

		document.getElementById('frm_createNewAccount01:inpf_hidden01').value = "true";

		// naechste drei Zeilen sind dafuer da, um zu gucken, ob es richtig lauft... spaeter loeschen
		//pwd01 = document.getElementById('frm_createNewAccount01:inpf_pwd01').value;
		//pwd02 = document.getElementById('frm_createNewAccount01:inpf_pwd02').value;
		//document.getElementsByName('message')[0].innerHTML = sha256(pwd02);

		return true;
	} else {
		document.getElementsByName('message')[6].innerHTML = "Kennwoerter mussen uebereinstimmen";
		return false;
	}



}
