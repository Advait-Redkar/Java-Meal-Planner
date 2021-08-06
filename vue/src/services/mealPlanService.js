import axios from "axios";


export default{

    addMealPlan(mealPlan){
        return axios.post('/createmealplan',mealPlan)
    },

    viewMealPlans(){
        return axios.get('/mealplans')
    }


}