/**
 * erstellt von Juri Rempel
 * 
 */
var i = 0;
var j8eePrepImgs = [
    [
        "https://i.imgur.com/Gabe9EX.jpg",
        "https://i.imgur.com/VlM5RPq.jpg",
        "https://i.imgur.com/cKn8nFm.jpg",
        "https://i.imgur.com/Vk6CMkX.jpg",
        "https://i.imgur.com/nGkIZQY.jpg",
        "https://i.imgur.com/Stjauoc.jpg",
        "https://i.imgur.com/FSqcQQ4.jpg",
        "https://i.imgur.com/5MtJ8Pz.jpg",
        "https://i.imgur.com/aLESaPY.jpg",
        "https://i.imgur.com/88Wv0y6.jpg"
    ],
    [
        "https://i.imgur.com/EsrUx94.jpg",
    ],
    [
        "https://i.imgur.com/XblWquZ.jpg",
        "https://i.imgur.com/yBxLLNN.jpg"
    ], 
    [
        "https://i.imgur.com/FeZ02aQ.jpg", 
        "https://i.imgur.com/0OjcGjv.jpg", 
        "https://i.imgur.com/zE0YmsO.jpg"
    ]
]; 

var legends = [
    [
        "Text1", "Text2", "Text3", "Text4", "Text5", "Text6", "Text7", "Text8", "Text9", "Text10"
    ], 
    [
        "Den Download Link für Tomcat 9 Version finden Sie link im Download Bereich der Webseite. Wir werden diese Version des Tomcat Servers herunterladen."
    ], 
    [
        "Meiner Empfehlung nach sollen Sie die 32-bit bzw. 64-bit Windows zip Datei herunterladen.", "<strong>Die Installation vom Tomcat ist sehr einfach. </strong><br /><ul><li>Wählen Sie den Menüpunkt <i>Extract files...</i> aus</li><li>Und im nächsten Schritt wählen Sie ihren Zielordner aus. Auf meinem Rechner ist es Laufwerk <strong>C:\\</strong></li></ul>"
    ], 
    [ 
        "<strong>Tastenkombination [Win]+[Pause] öffnet schnell die Systemeigenschaften.</strong><br />" +
        "<ul><li>Klicken Sie links im Fenster auf den Link <i>Erweiterte Systemeinstellungen</i></li>" +
        "<li>Klicken Sie anschließend auf den Button <i>Umgebungsvariablen</i></li>", "<strong>Es öffnet sich das Fenster <i>Umgebungsvariablen</i>" +
        		"</strong><ul><li>Klicken Sie unter Benutzervariablen auf <i>Neu..</i></li></ul>", 
        		"<ul><li>Geben Sie den <i>Namen der Variablen</i> als JAVA_HOME ein</il>" +
        		"<li>und den <i>Wert der Variablen</i> als Installationspfad Ihres Tomcat Servers</li>" +
        		"<li>Klicken Sie auf <i>OK</i></li></ul>"
    ]
];

function prevImage(category){
    var imgLegend;
    var classAttr = "";
    var len;

    len = j8eePrepImgs[category].length;
    imgLegend = legends[category];

    i--;
    if(i >= len) i = len - 2;
    if(i < 0) i = 0;

    var path = j8eePrepImgs[category][i];
    classAttr = document.getElementById("carousel-img-id-0" + category);
    classAttr.setAttribute("src", path);
    
    document.getElementById("img-legend-0" + category).innerHTML = imgLegend[i];
}

function nextImage(category) {
    var imgLegend;
    var classAttr = "";
    var len;
    
    len = j8eePrepImgs[category].length;
    imgLegend = legends[category];

    i++;
    if( i <= 0) i = 1;
    if(i >= len) i = len - 1; 

    var path = j8eePrepImgs[category][i]; 
    classAttr = document.getElementById("carousel-img-id-0" + category);
    classAttr.setAttribute("src", path);

    document.getElementById("img-legend-0" + category).innerHTML = imgLegend[i];

} 

function getDotImage(category, image) {
    var imgLegend;
    var htmlTag;
    var path; 

    i = image;
    path = j8eePrepImgs[category][image];
    imgLegend = legends[category];

    htmlTag = document.getElementById("carousel-img-id-0" + category);
    htmlTag.setAttribute("src", path);
    document.getElementById("img-legend-0" + category).innerHTML = imgLegend[i];

}

function setNecessaryNumberOfDots(category){
	var imgLegend;
    var length;
    
    imgLegend = legends[category];
    length = j8eePrepImgs[category].length;

    for(var i = 0; i < length; i++) {
        var HElem = document.getElementById("dot-container-id-0" + category);
        var mySPAN = document.createElement("span");
        mySPAN.id = "dot-" + i;
        mySPAN.className = "dot";
        mySPAN.setAttribute("onclick", "getDotImage(" + category + "," + i + ")");
        HElem.appendChild(mySPAN);

    }

    document.getElementById("img-legend-0" + category).innerHTML = imgLegend[0];
}
