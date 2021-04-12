<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3 class="w-100 text-center  my-5">Добавить предмет</h3>
        <div class="" style="width: 50rem; margin: 0 auto">
            <a class="btn btn-warning my-2" href="uploadForm">Загрузить картинку товара</a>
            <form action="createFurniture" method="POST">
                  <div class="mb-3 row">
                    <label for="name" class="col-sm-3 col-form-label">Название предмета:</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control" id="name" name="name" value="${name}">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="size" class="col-sm-3 col-form-label">Фирма:</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control" id="color" name="size" value="${size}">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="quantity" class="col-sm-3 col-form-label">Количество:</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control" id="quantity" name="quantity" value="${quantity}">
                    </div>
                  </div>
                  <div class="form-floating">
                    <textarea class="form-control" placeholder="text" name="text" id="text"></textarea>
                    <label for="text">Описание</label>
                  </div><br>
                  <div class="mb-3 row">
                    <label for="price" class="col-sm-3 col-form-label">Цена товара:</label>
                    <div class="col-sm-9">
                      <input type="text" class="form-control" id="price" name="price" value="${price}">
                    </div>
                  </div>
                  <div class="mb-3 row">
                    <label for="login" class="col-sm-3 col-form-label">Фото: </label>
                    <div class="col-sm-9">
                        <select class="form-select" name="coverId">
                            <option value="">Выберите фото предмета</option>
                            <c:forEach var="cover" items="${listCovers}">
                                <option value="${cover.id}">${cover.description}</option>
                            </c:forEach>
                        </select>
                    </div>
                  </div>
                <div class="col-sm-12">
                  <button type="submit" class="btn btn-warning mb-3 w-100">Отправить</button>
                </div>
            </form>
        </div>


