/* function to add classes for restricting inputs in text fields start  */
      $(document).ready(function() 
      {
	$('.inptcap').on( "copy cut paste drop", function() {
                return false;
        });
        
        $('.alphanum').keypress(function (e) {
            var regex = new RegExp("^[a-zA-Z0-9]+$");
            var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
            if (regex.test(str)) {
                return true;
            }
            e.preventDefault();
            return false;
        });
        $('.alpha').keypress(function (e) {
            var regex = new RegExp("^[a-zA-Z]+$");
            var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
            if (regex.test(str)) {
                return true;
            }
            e.preventDefault();
            return false;
        });
        $('.name').keypress(function (e) {
            var regex = new RegExp("^[a-zA-Z .]+$");
            var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
            if (regex.test(str)) {
                return true;
            }
            e.preventDefault();
            return false;
        });
        $('.numb').keypress(function (e) {
            var regex = new RegExp("^[0-9]+$");
            var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
            if (regex.test(str)) {
                return true;
            }
            e.preventDefault();
            return false;
        });
        $('.excludechr').keypress(function (e) {
            var regex = new RegExp("[^\'/&#]+$");
            var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
            if (regex.test(str)) {
                return true;
            }
            e.preventDefault();
            return false;
        });
      });
/* function to add classes for restricting inputs in text fields end  */