<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
        <div class="col-12">
          <div class="card my-4">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
              <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
                <h6 class="text-white text-capitalize ps-3">Area wise</h6>
              </div>
            </div>
            <div class="card-body px-0 pb-2">
              <div class="table-responsive p-0">
                <table class="table align-items-center justify-content-center mb-0">
                  <thead>
                    <tr>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Area</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">CSC</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Application Count</th>
                      
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${bottomList}" var="row">
                    <tr>
                      <td>
                        <div class="d-flex px-2">
                          
                          <div class="my-auto">
                            <h6 class="mb-0 text-sm">${row.code}</h6>
                          </div>
                        </div>
                      </td>
                      <td>
                        <p class="text-sm font-weight-bold mb-0">${row.name}</p>
                      </td>
                      <td>
                        <span class="text-xs font-weight-bold">${row.count1}</span>
                      </td>
                      
                      
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
      