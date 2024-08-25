# Tool Telegram

Welcome to Tool Telegram! This project is designed for Telegram group and channel administrators, as well as those working with bots. Tool Telegram ensures that your ID and other sensitive information are not disclosed.

## Introduction

Our goal is to integrate multiple tools into a single platform, providing an exceptional user experience. We developed this application with a focus on usability and efficiency, so you can focus on what truly matters.

## About the Creator

Tool Telegram was developed by AGL SK, a professional passionate about technology and innovation. With extensive experience in creating digital solutions, AGL SK is dedicated to delivering high-quality products that meet users' needs.

## Documentation

Find all the necessary documentation to efficiently use Tool Telegram. Here you will have access to tutorials, FAQs, and detailed guides that will help you make the most out of all the features offered.

[Access the Documentation](https://aglsk.github.io/Tool-Telegram-Oficial/documentation)

## Usage

Tool Telegram allows you to send messages to your group or channel using its bot, ensuring your anonymity.

## Authentication

To authenticate, provide your Chat ID and Bot API Token:

- **Chat ID**: Your Chat ID Here
- **Token**: Your Bot API Token Here

## Endpoints

### POST /Group or Channel Without Topic

#### Request Body

- **Message**: The message you want to send
- **Photo** (optional): An optional photo to include with the message

#### Response

- **Success**: "Message sent successfully!"

### POST /Group or Channel With Topic

#### Request Body

- **Message**: The message you want to send
- **Photo** (optional): An optional photo to include with the message
- **Topic ID** (optional): The ID of the topic within the group or channel

#### Response

- **Success**: "Message sent successfully!"

## License

This software is distributed under the terms of the [GNU General Public License](https://www.gnu.org/licenses/gpl-3.0.html) as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

Please refer to the license for full details on the rights and restrictions associated with using this software.
