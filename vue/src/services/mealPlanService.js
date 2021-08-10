import axios from "axios";


export default{

    addMealPlan(mealPlan){
        return axios.post('/createmealplan',mealPlan)
    },

    viewMealPlans(){
        return axios.get('/mealplans')
    },

    viewMealPlanDetails(mealplanId){
        return axios.get(`/mealplans/${mealplanId}`)
    },
    
    editMealPlan(mealplanId, mealPlan){
        return axios.put(`/mealplans/edit/${mealplanId}`, mealPlan)
    },

    viewGroceries(mealplanId){
        return axios.get(`/mealplans/${mealplanId}/groceries`)
    }



}