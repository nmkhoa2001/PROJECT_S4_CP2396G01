document.getElementById("file").onchange = function () {
    var reader = new FileReader();

    reader.onload = function (e) {
        // get loaded data and render thumbnail.
        document.getElementById("image").src = e.target.result;
    };

    // read the image file as a data URL.
    reader.readAsDataURL(this.files[0]);
};

// order
function viewOrder() {
    $('#view-order').click();
}
//product
function viewProduct() {
    $('#view-product').click();
}