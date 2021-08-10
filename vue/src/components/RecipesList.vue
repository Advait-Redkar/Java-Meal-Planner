<template>
  <div class="main-div">
      <div class="div-subclass">
       <div class="recipe-name">   
      <h1>Recipe List</h1>
       </div>
      <div v-for="recipe in recipes" v-bind:key="recipe.recipeId">
          <div class="list-btn-div">
              <router-link v-bind:to="{name: 'recipeDetails', params: {recipeId:recipe.recipeId}}">
              <button class="list-button">{{recipe.recipeName}}</button>
              </router-link>
              </div>
      </div>
      </div>

  </div>
</template>

<script>
import recipeService from "@/services/RecipeService";

export default {
    name:"recipe-list",
    data(){
        return{
            recipes:[]
        }
    },
    created(){
            recipeService
                .listAllRecipes()
                .then((response)=>{
                    this.recipes = response.data;
                });
        }
    }

</script>

<style>
.list-button{
display:inline-block;
padding:0.5em 3em;
border:0.16em solid #F38630;
margin:0 0.3em 0.3em 0;
box-sizing: border-box;
text-decoration:none;
text-transform:uppercase;
font-family:'Roboto',sans-serif;
font-weight:400;
background-color:white;
text-align:center;
transition: all 0.15s;
color:black;
width: 250px;
}

.list-btn-div{
    display: flex;
   /* flex-wrap: wrap;
    width: 200px;*/
}


/*added this */
@media only screen and (max-width: 885px) {
.list-button{
    display: flex;
    flex-wrap: flex;
    width: 100px;
    font-size: .7em;
    text-decoration: none;
}

}

</style>