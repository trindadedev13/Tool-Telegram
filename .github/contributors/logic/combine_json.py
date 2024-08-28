import json

# Load data from the API
with open('.github/contributors/contributors_github_temp.json', 'r') as f:
    github_data = json.load(f)

# Load existing data
with open('.github/contributors/contributors_github.json', 'r') as f:
    existing_data = json.load(f)

# Print types and sample data to debug
print(f"Type of github_data: {type(github_data)}")
print(f"Type of existing_data: {type(existing_data)}")
print(f"Sample github_data entry: {github_data[0] if github_data else 'No data'}")
print(f"Sample existing_data entry: {existing_data[0] if existing_data else 'No data'}")

# Convert existing data to a dictionary based on login and id
existing_dict = {(user['login'], user['id']): user for user in existing_data}

# Update or add API data
for user in github_data:
    key = (user['login'], user['id'])
    if key in existing_dict:
        existing_dict[key].update(user)
    else:
        existing_dict[key] = user

# Convert back to a list
combined_data = list(existing_dict.values())

# Save the combined JSON
with open('.github/contributors/contributors_combined.json', 'w') as f:
    json.dump(combined_data, f, indent=2)
