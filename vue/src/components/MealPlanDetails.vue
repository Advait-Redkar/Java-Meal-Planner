<template>
  <div class="main-div">
    <div class="div-subclass">
      <div class="recipe-name">
        <h1>{{ mealplan.mealplanName }}</h1>
      </div>
      <div>
        <h2>{{dayOfWeekMapping[mealplan.mealplanDay]}}</h2>
      </div>
      <div>
        
        <ul
          id="example-1"
          v-for="meals in mealplan.mealList"
          v-bind:key="meals.mealName"
        >
          <h3>
             {{mealTypeMapping[meals.mealType]}}
            <li>
             
              {{ meals.mealName }}
              
            </li>
          </h3>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    mealplan: Object,
  },
  data(){
    return{
      dayOfWeekMapping:["unused","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"],
      mealTypeMapping:["unused","Breakfast","Lunch","Dinner","Snack"]
    }
  }
};
</script>

<style>
</style>