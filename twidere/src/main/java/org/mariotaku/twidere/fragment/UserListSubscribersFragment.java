/*
 * 				Twidere - Twitter client for Android
 * 
 *  Copyright (C) 2012-2014 Mariotaku Lee <mariotaku.lee@gmail.com>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mariotaku.twidere.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import org.mariotaku.twidere.loader.CursorSupportUsersLoader;
import org.mariotaku.twidere.loader.UserListSubscribersLoader;
import org.mariotaku.twidere.model.UserKey;

public class UserListSubscribersFragment extends CursorSupportUsersListFragment {

    @Override
    public CursorSupportUsersLoader onCreateUsersLoader(final Context context, @NonNull final Bundle args, boolean fromUser) {
        final String listId = args.getString(EXTRA_LIST_ID);
        final UserKey accountKey = args.getParcelable(EXTRA_ACCOUNT_KEY);
        final UserKey userKey = args.getParcelable(EXTRA_USER_KEY);
        final String screenName = args.getString(EXTRA_SCREEN_NAME);
        final String listName = args.getString(EXTRA_LIST_NAME);
        final UserListSubscribersLoader loader = new UserListSubscribersLoader(context, accountKey,
                listId, userKey, screenName, listName, getData(), fromUser);
        loader.setCursor(getNextCursor());
        loader.setPage(getNextPage());
        return loader;
    }

}
