$(document).ready(function() {
    $(window).scrollTop(0);
    
$(".introducePage_h2_font1").css("transform","translateY(0px)");
$(".introducePage_h2_font1").css("opacity","1");

setTimeout(function(){
   $(".introducePage_h2_font2").css("transform","translateY(0px)");
   $(".introducePage_h2_font2").css("opacity","1");
},200)
 });




$(function(){
    $(".nav_ul").mouseover(function(){
        $(".section1").stop().slideDown(150);
        $(".section1").css("display", "grid");
     })
     
    $(".nav_ul").mouseout(function(){
     $(".section1").stop().slideUp(50);
  })



  $(".li_1").mouseover(function(){
    $(".stick").css("border", "3.5px solid rgb(106, 141, 218)");
    $(".stick").css("transform", ": scale(1.5)");
    $(".stick").css("width", "150px");
    $(".stick").css("transform-origin", "50% 50%");
 });
 $(".li_1").mouseout(function(){
    $(".stick").stop().css("transform", ": scale(0)");
    $(".stick").stop().css("border", "0px solid black");
    $(".stick").stop().css("width", "0px");
 })
 $(".li_2").mouseover(function(){
    $(".stick2").css("border", "3.5px solid rgb(106, 141, 218)");
    $(".stick2").css("transform", ": scale(1.5)");
    $(".stick2").css("width", "150px");
    $(".stick2").css("transform-origin", "50% 50%");
 });
 $(".li_2").mouseout(function(){
    $(".stick2").stop().css("transform", ": scale(0)");
    $(".stick2").stop().css("border", "0px solid black");
    $(".stick2").stop().css("width", "0px");
 })
 $(".li_3").mouseover(function(){
    $(".stick3").css("border", "3.5px solid rgb(106, 141, 218)");
    $(".stick3").css("transform", ": scale(1.5)");
    $(".stick3").css("width", "150px");
    $(".stick3").css("transform-origin", "50% 50%");
 });
 $(".li_3").mouseout(function(){
    $(".stick3").css("transform", ": scale(0)");
    $(".stick3").css("border", "0px solid black");
    $(".stick3").css("width", "0px");
 })
 $(".li_4").mouseover(function(){
    $(".stick4").css("border", "3.5px solid rgb(106, 141, 218)");
    $(".stick4").css("transform", ": scale(1.5)");
    $(".stick4").css("width", "150px");
    $(".stick4").css("transform-origin", "50% 50%");
 });
 $(".li_4").mouseout(function(){
    $(".stick4").css("transform", ": scale(0)");
    $(".stick4").css("border", "0px solid black");
    $(".stick4").css("width", "0px");
 })

 $(window).scroll(function(){
    let scrollTopWindow = $(this).scrollTop();

    if(scrollTopWindow > 300){
        $(".introduce_h2_font_introdroduce").css("opacity", "1");
    }

    if(scrollTopWindow > 400){
        $(".introduce_div_2_box").css("opacity", "1");
        $(".introduce_div_2_box").css("transform","translateY(0px)");
    }

    if(scrollTopWindow > 1000){
        $(".introduce_middle_main_font_p1").css("opacity", "1");
        $(".introduce_middle_main_font_p1").css("transform","translateX(0px)");
    }

    if(scrollTopWindow > 1100){
        $(".introduce_middle_main_font_p2").css("opacity", "1");
        $(".introduce_middle_main_font_p2").css("transform","translateX(0px)");
    }
 })



})