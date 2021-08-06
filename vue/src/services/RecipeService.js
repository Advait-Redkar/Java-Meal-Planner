import axios from "axios";


export default{

    addRecipe(recipe){
        return axios.post('/createrecipe',recipe)
    },
    listAllRecipes(){
        return axios.get('/recipes')
    },   

    getRecipeDetails(recipeId){
        return axios.get(`/recipe/${recipeId}`)
    },

    editRecipe(recipeId, recipe){
        return axios.put(`/recipe/edit/${recipeId}`, recipe)
    },


}