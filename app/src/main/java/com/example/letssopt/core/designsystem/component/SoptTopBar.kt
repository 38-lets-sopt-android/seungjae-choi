package com.example.letssopt.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.common.extension.noRippleClickable
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun SoptTopBar(
    modifier: Modifier = Modifier,
    onWatchClick: () -> Unit = {},
    onNotiClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 23.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_watch),
            contentDescription = null,
            tint = LETSSOPTTheme.colors.textPrimary,
            modifier = Modifier.noRippleClickable(onClick = onWatchClick)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_noti),
            contentDescription = null,
            tint = LETSSOPTTheme.colors.textPrimary,
            modifier = Modifier.noRippleClickable(onClick = onNotiClick)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_profile),
            contentDescription = null,
            tint = LETSSOPTTheme.colors.textPrimary,
            modifier = Modifier.noRippleClickable(onClick = onProfileClick)
        )
    }
}

@Preview
@Composable
private fun SoptTopBarPreview() {
    SoptTopBar()
}