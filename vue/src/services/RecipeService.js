import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default{

    addRecipe(recipe){
        return http.post('/createrecipe',recipe)
    },
    listAllRecipes(){
        return http.get('/recipes')
    },   

    getRecipeDetails(recipeId){
        return http.get(`/recipe/${recipeId}`)
    }


}