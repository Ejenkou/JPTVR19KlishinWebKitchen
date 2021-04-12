<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Редактирование товара</h1>

        <form action="editFurniture" method="POST">
            <input type="hidden" name="furnitureId" value="${furniture.id}">
            Название товара: <input type="text" name="name" value="${furniture.name}"><br>
            Фирма товара: <input type="text" name="size" value="${furniture.size}"><br>
            Количество товара: <input type="text" name="quantity" value="${furniture.quantity}"><br>
            Цена товара: <input type="text" name="price" value="${furniture.price}"><br>
            <input type="submit" name="submit" value="Изменить пиццу">
        </form>
    