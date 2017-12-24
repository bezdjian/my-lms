var itemCount = 0;
var totalPrice = 0;

$(document).ready(function(){
   //DataTable
   $("#all-user-table").dataTable();
   $("#all-enrolled-user-table").dataTable();


    //Slide down shopping-cart-box
    $(".fa-shopping-cart").mouseover(function(){
        $(".shopping-cart-box").slideDown();
    });
    $(document).click(function(){
        $(".shopping-cart-box").slideUp();
    })
    //.mouseleave(function(){
    //    $(".shopping-cart-box").slideUp();
    //});

    //Checkout confirm dialog.
    $("#dialog").dialog({
        autoOpen: false,
        modal: true,
        buttons : {
            "Confirm" : function() {
                //Do stuff
                console.log("You have confirmed!");
                //Do the checkout transaction
                onClickCheckout();
                //Open congrats dialog
                $("#dialog-congrats").dialog("open");
            },
            "Cancel" : function() {
                //Just close
                $(this).dialog("close");
            }
        }
    });

    //Checkout Success dialog.
    $("#dialog-congrats").dialog({
        autoOpen: false,
        modal: true,
        buttons : {
            "OK" : function() {
                //Just close
                $(this).dialog("close");
            }
        }
    });

    //Disable in the beginning
    $(".checkout").prop('disabled', true);

    //On checkout, show dialog to confirm.
    $(".checkout").on("click", function(e) {
        e.preventDefault();
        $("#dialog").dialog("open");
    });

    //Clear basket and cookie.
    $('.empty_cart').click(function() {
        emptyShoppingCart();
    });



    //Loop cookie array to re-add basket count and items in the shopping-box.
    //Get the cookie array.
    var items = $.cookie();
    for(var key in items){
        var product = items[key];
        console.log("Object: " + product.productName);
        //Add numbers on the cart
        printCartContent(product.productName, product.productPrice, product.productCurrency);
        //Enable checkout button since we have products in the cookie. (this runs when refreshing)
        $(".checkout").prop('disabled', false);
    }

});

$.cookie.json = true;
function AddToCart(productid, name, price, currency){
    printCartContent(name, price, currency);
    //Add to jQuery Cookie
    var itemsObject = {productId:productid, productName:name, productPrice:price, productCurrency:currency};
    $.cookie('item-'+productid, itemsObject);
    //Enable checkout button
    $(".checkout").prop('disabled', false);
}

function printCartContent(name, price,currency){
    itemCount++
    $('#itemCount').html(itemCount).css('display', 'block');
    //Hide Empty Basket text
    $(".empty-basket").css("display", "none");
    //Append to shopped-items
    $(".shopped-items").append("<div class='summaryShipping'>" +
        "<div class='itemName'>" + name + "</div>" +
        "<div class='itemPrice'>"+price+currency +"</div>" +
        "</div>");
    //Total price
    totalPrice += parseInt(price);
    $(".shopped-items-footer").html("<div class='summaryShipping'>" +
        "<div class='itemTotalName'>Total:</div>" +
        "<div class='itemTotalPrice'>"+totalPrice+currency+"</div>" +
        "</div>");
}


function onClickCheckout(){

    var contextPath = $("#contextPath").val();
    var personid = $("#personid").val();

    var productIDs = [];
    var items = $.cookie();
    for(var key in items){
        var product = items[key];
        productIDs.push(product.productId);
    }

    var data = 'personid='+personid+'&productids='+productIDs;
    //console.log("DATA: " + data);
    $.ajax({
        type : "GET",
        //contentType : "application/json",
        url : contextPath + "/checkout",
        data : data,
        dataType : 'html',
        timeout : 100000,
        success : function(data) {
            //console.log("success DATA: " + data);
            if(data.indexOf('done') >= 0){
                //Close the confirmation dialog
                $("#dialog").dialog("close");
                //Clear cookie/shopping-cart
                emptyShoppingCart();
            }else{
                console.log("IN ELSE");
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(thrownError);
            return "error";
        }
    });
}

function emptyShoppingCart(){
    itemCount = 0;
    totalPrice = 0;
    $('#itemCount').html('').css('display', 'none');
    $('#cartItems').html('');
    $('.shopped-items').html('');
    $(".shopped-items-footer").html("<div class='summaryShipping'>" +
        "<div class='itemTotalName'>Total:</div>" +
        "<div class='itemTotalPrice'>0</div>" +
        "</div>");
    var cookies = $.cookie();
    for(var cookie in cookies) {
        $.removeCookie(cookie);
    }
    //Disable checkout button
    $(".checkout").prop('disabled', true);
}