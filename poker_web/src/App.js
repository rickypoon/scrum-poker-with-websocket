import 'bootstrap/dist/css/bootstrap.css';
import './App.css';

function App() {
  return (
    <div className="App">
      <h1 class="text-center">Sprint backlog</h1>
      <ul class="list-group">
        <li class="list-group-item list-group-item-action flex-column align-items-start active">
          <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">implement chat feature</h5>
            <select>
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>5</option>
              <option>8</option>
              <option>13</option>
              <option>21</option>
              <option>no_idea</option>
              <option>resign</option>
            </select>
          </div>
          <p class="mb-1">A gave 2 points, B gave 2 points, B gave 3 points</p>
        </li>
        <li class="list-group-item list-group-item-action flex-column align-items-start">
          <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">add social login</h5>
            <select disabled>
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>5</option>
              <option>8</option>
              <option>13</option>
              <option>21</option>
              <option>no_idea</option>
              <option>resign</option>
            </select>
          </div>
          <p class="mb-1">A gave 2 points, B gave 3 points</p>
        </li>
        <li class="list-group-item list-group-item-action flex-column align-items-start">
          <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">implement profile pic upload</h5>
            <select disabled>
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>5</option>
              <option>8</option>
              <option>13</option>
              <option>21</option>
              <option>no_idea</option>
              <option>resign</option>
            </select>
          </div>
          <p class="mb-1">A gave 2 points, B gave 3 points</p>
        </li>
      </ul>
    </div>
  );
}

export default App;
