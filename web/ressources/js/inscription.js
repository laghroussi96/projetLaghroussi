$(document).ready(function () {

    var navListItems = $('div.setup-panel div a'),
        allWells = $('.setup-content'),
        allNextBtn = $('.nextBtn'),
        allPevBtn=$('.pevBtn');
    allWells.hide();

    navListItems.click(function (e) {
        e.preventDefault();
        var $target = $($(this).attr('href')),
            $item = $(this);

        if (!$item.hasClass('disabled')) {
            navListItems.removeClass('btn-success').addClass('btn-default');
            $item.addClass('btn-success');
            allWells.hide();
           $target.show();
           $target.find('input:eq(0)').focus();
        }
    });

    allNextBtn.click(function () {
        var curStep = $(this).closest(".setup-content"),
            curStepBtn = curStep.attr("id"),
            nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
            curInputs = curStep.find(".text"),
            isValid = true;

        $(".form-group").removeClass("has-error");
        for (var i = 0; i < curInputs.length; i++) {
            if (!curInputs[i].validity.valid) {
                isValid = false;
                $(curInputs[i]).closest(".form-group").addClass("has-error");
            }
        }

        if (isValid) nextStepWizard.removeAttr('disabled').trigger('click');
    });

    $('div.setup-panel div a.btn-success').trigger('click');

     $(".accordion-head").click(function() {
    if ($('.accordion-body').is(':visible')) {
      $(".accordion-body").slideUp(300);
      $(".plus").text('+');
    }
    if ($(this).next(".accordion-body").is(':visible')) {
      $(this).next(".accordion-body").slideUp(300);
      $(this).children(".plus").text('+');
    } else {
      $(this).next(".accordion-body").slideDown(300);
      $(this).children(".plus").text('-');
    }
  });
     $('input[type=checkbox]').change(function(e){
   if ($('input[type=checkbox]:checked').length > 10) {
        $(this).prop('checked', false)
       
   }
});
      var $checkboxes = $('#msform div input[type="checkbox"]');
        
    $checkboxes.change(function(){
        var countCheckedCheckboxes = $checkboxes.filter(':checked').length;
        // $('#count-checked-checkboxes').text(countCheckedCheckboxes);
        
        $('#sp').text(countCheckedCheckboxes);
    });
      
 
    

});