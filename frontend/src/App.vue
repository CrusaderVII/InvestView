<script setup>
</script>

<template>
  <main>
    <div class="input-wrapper">
      <div class="input-box">
        <fa class="icon" icon="search"></fa>
        <input type="search" placeholder="Enter issuer name: GAZP" class="main-input">
      </div>
    </div>
  </main>
</template>

<style scoped>
  main{
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    background-color: #1A1A1A;
  }

  .input-wrapper{
    margin-top: 35vh;
    width: 40vw;
    height: 50px;
    border-radius: 20px;
    border: 2px solid #42B883;
    background-color: #2F2F2F;
  }

  .input-box {
    display: flex;
    widows: 100%;
    height: 100%;
    align-items: center;
  }

  .main-input{
    width: 100%;
    height: 100%;
    border: none;
    outline: none;
    color: #A0A0A0;
    font-size: large;
    padding-left: 10px;
    background-color: transparent;
  }

  .icon {
    color: #A0A0A0;
    margin-left: 10px;   
    margin-bottom: 5px; 
  }
</style>
