import axios from "axios";


export default{

    addMeal(meal){
        return axios.post('/createmeal', meal)
    },

    viewMeals(){
        return axios.get('/meals')
    }


}