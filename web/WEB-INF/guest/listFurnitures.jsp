<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <h3 class="w-100 text-center  my-5">Товары:</h3>
        <div class="w-100 d-flex justify-content-center">
            <c:forEach var="furniture" items="${listFurnitures}">
                <div class="card m-2" style="min-width: 12rem;">
                    <img src="insertFile/${furniture.cover.path}"  class="card-img-top" alt="..." style="max-width: 12rem; max-height: 15rem">
                        <div class="card-body">
                            <h5 class="card-title">${furniture.name}</h5>
                                Фирма товара: <p class="card-text">${furniture.size} </p>
                                Количество товара: <p class="card-text">${furniture.quantity} </p>
                                Цена товара: <p class="card-text">${furniture.price}$ </p>
                                Описание товара: <p class="card-text">${furniture.text} </p>
                                <a href="purchaseFurniture?furnitureId=${furniture.id}" class="btn btn-warning">Купить Товар</a>
                        </div>
                </div>
            </c:forEach>
        </div>
        