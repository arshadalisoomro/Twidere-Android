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

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

import org.mariotaku.twidere.R;
import org.mariotaku.twidere.model.ParcelableUser;
import org.mariotaku.twidere.util.AsyncTwitterWrapper;

public class CreateUserMuteDialogFragment extends BaseDialogFragment implements DialogInterface.OnClickListener {

    public static final String FRAGMENT_TAG = "create_user_mute";

    @Override
    public void onClick(final DialogInterface dialog, final int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                final ParcelableUser user = getUser();
                final AsyncTwitterWrapper twitter = mTwitterWrapper;
                if (user == null || twitter == null) return;
                twitter.createMuteAsync(user.account_key, user.key);
                break;
            default:
                break;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final FragmentActivity activity = getActivity();
        final Context context = activity;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final ParcelableUser user = getUser();
        if (user != null) {
            final boolean nameFirst = mPreferences.getBoolean(KEY_NAME_FIRST);
            final String displayName = mUserColorNameManager.getDisplayName(user, nameFirst);
            builder.setTitle(getString(R.string.mute_user, displayName));
            builder.setMessage(getString(R.string.mute_user_confirm_message, displayName));
        }
        builder.setPositiveButton(android.R.string.ok, this);
        builder.setNegativeButton(android.R.string.cancel, null);
        return builder.create();
    }

    private ParcelableUser getUser() {
        final Bundle args = getArguments();
        if (!args.containsKey(EXTRA_USER)) return null;
        return args.getParcelable(EXTRA_USER);
    }

    public static CreateUserMuteDialogFragment show(final FragmentManager fm, final ParcelableUser user) {
        final Bundle args = new Bundle();
        args.putParcelable(EXTRA_USER, user);
        final CreateUserMuteDialogFragment f = new CreateUserMuteDialogFragment();
        f.setArguments(args);
        f.show(fm, FRAGMENT_TAG);
        return f;
    }
}
