import os

from slack_sdk import WebClient
from slack_sdk.errors import SlackApiError
 
# ID of the channel you want to send the message to

slack_token = 'xoxb-4969505888997-4981675662485-4QbvxnzjBVwPwcKRFfwzfag5'
client = WebClient(token=slack_token)

try:
    # Call the chat.postMessage method using the WebClient
    
    response = client.chat_postMessage(
        channel = "C04VA42SX08",
        text="Hello world :shipit:"
    )
 
except SlackApiError as e:
    assert e.response["error"]
    print(f"Error posting message: {e}")


    
