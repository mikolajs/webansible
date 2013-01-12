#!/bin/bash

valac --pkg gio-2.0 --pkg libsoup-2.4 apt_follower_client.vala apt_connector.vala -o apt_follower_client
