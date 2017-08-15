package org.launchcode.cheesemvc;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
//specify that every value here within each controller should be proceeded with /cheese
@RequestMapping("cheese")
public class CheeseController {

    //create an arraylist that holds strings (names of the cheeses)
    //static is necessary to make list accessible to the below methods
    //note that adding an arraylist is not a replacement for a database.  Whenever we restart, our data will vanish
    static ArrayList<String> cheeses = new ArrayList<>();

    @RequestMapping(value = "")
    //public method that returns a string
    public String index(Model model) {

        //add a few cheeses to the list
        //cheeses.add("Cheddar");
        //cheeses.add("Parmesan");
        //cheeses.add("Munster");

        //pass that list into the view
        //key named cheeses
        model.addAttribute("cheeses", cheeses);

        //will take two values in a key value pair
        //the first value is the key
        //the second will pass in string that is title of the page that comes from the controller
        model.addAttribute("title", "My Cheeses");
        //name of template that is going to render
        return "cheese/index";
    }
    //form display
    //add controller method to display a form so that user can input cheeses
    //located at the 'add' url
    //display of the form is going to be a get request
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        //add a title (need model attribute above to do that)
        model.addAttribute("title", "Add Cheese");
        //form will be called add
        return "cheese/add";

    }
    //process form
    //method named processAddCheeseForm
    //processing of the form will be a POST request
    @RequestMapping(value = "add", method = RequestMethod.POST)
    //my controller method expects to be passed a parameter that is a string and we will refer to that as cheeseName
    //RequestParam annotation says Spring should look for a request parameter with the same name as the method parameter
    //and if it finds it enter it into this method call.
    //In other words, spring will look for a request parameter based on the name of the method input parameter
    //method input parameter(cheeseName) needs to match name on line 23 of add.html file
    public String processAddCheeseForm(@RequestParam String cheeseName) {
        cheeses.add(cheeseName);
        //redirect to /cheese (we're in the cheese controller so by leaving this empty, will redirect to the /cheese handler
        return "redirect:";
    }
}
