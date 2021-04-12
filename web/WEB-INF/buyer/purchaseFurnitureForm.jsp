<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="w-100 my-4">
        <div class="row w-100 d-flex justify-content-center border">
            <h4> Были распроданы  следующие товары:</h4>
        </div>
        <div class="row w-100 d-flex justify-content-center">
            <c:forEach var="furniture" items="${listBoughtFurnitures}">
                <div class="card col m-2 text-center" style="min-width: 12rem;">
                <img src="insertFile/${furniture.cover.path}" class="mx-auto img-thumbnail" style="max-width: 7rem; max-height: 10rem" class="card-img-top" alt="${furniture.cover.description}">
                <div class="card-body">
                  <h5 class="card-title">Название товара: ${furniture.name}</h5>
                  <p class="card-text">Фирма товара: ${furniture.size}</p>
                  <p class="card-text">Цена товара: ${furniture.price}€</p>
                  <p class="card-text">Описание товара: ${furniture.text}</p>
                </div>
              </div>       
            </c:forEach>
        </div>
    </div>