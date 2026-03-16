import requests
import json

BASE_URL = "http://localhost:9090"

def test_api():
    # 1. Login to get token
    login_data = {"email": "admin@gov.in", "password": "admin123"}
    try:
        response = requests.post(f"{BASE_URL}/api/auth/login", json=login_data)
        if response.status_code != 200:
            print(f"Login failed: {response.status_code} {response.text}")
            return
        
        token = response.json().get("token")
        print(f"Login successful. Token: {token[:20]}...")

        # 2. Call schemes API with category filter
        headers = {"Authorization": f"Bearer {token}"}
        params = {"category": "Agriculture"}
        
        print(f"Calling GET /api/schemes with params={params}")
        resp = requests.get(f"{BASE_URL}/api/schemes", headers=headers, params=params)
        
        print(f"Status Code: {resp.status_code}")
        if resp.status_code == 200:
            data = resp.json()
            print(f"Data returned (len={len(data)}):")
            print(json.dumps(data, indent=2))
        else:
            print(f"Error: {resp.text}")

    except Exception as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    test_api()
