<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Редактирование пиццы</h1>

        <form action="editFurniture" method="POST">
            <input type="hidden" name="furnitureId" value="${furniture.id}">
            Название пиццы: <input type="text" name="name" value="${furniture.name}"><br>
            Размер пиццы: <input type="text" name="size" value="${furniture.size}"><br>
            Количество пиццы: <input type="text" name="quantity" value="${furniture.quantity}"><br>
            Цена пиццы: <input type="text" name="price" value="${furniture.price}"><br>
            <input type="submit" name="submit" value="Изменить пиццу">
        </form>
    